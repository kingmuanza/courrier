package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CorrespondantDAO;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierProcedureEnCoursDAO;
import net.keama.workspace.dao.CourrierResponsableCommentaireDAO;
import net.keama.workspace.dao.CourrierResponsableDAO;
import net.keama.workspace.dao.EntreprisePartenaireDAO;
import net.keama.workspace.entity.Correspondant;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierProcedureEnCours;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.CourrierResponsableCommentaire;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Entreprise;
import net.keama.workspace.entity.EntreprisePartenaire;
import net.keama.workspace.entity.Structure;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.service.EmployeService;
import net.keama.workspace.service.StructureService;

@WebServlet(name = "PriseCourrierServlet", urlPatterns = {"/PriseCourrierServlet"})
public class PriseCourrierServlet extends HttpServlet {

    StructureService structureService = new StructureService();
    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();
    EmployeService employeService = new EmployeService();
    CorrespondantDAO correspondantDAO = new CorrespondantDAO();
    EntreprisePartenaireDAO entreprisePartenaireDAO = new EntreprisePartenaireDAO();
    CourrierResponsableCommentaireDAO courrierResponsableCommentaireDAO = new CourrierResponsableCommentaireDAO();
    CourrierProcedureEnCoursDAO courrierProcedureEnCoursDAO = new CourrierProcedureEnCoursDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur utilisateur = null;

        utilisateur = (Utilisateur) httpSession.getAttribute("utilisateur");
        int idemploye = utilisateur.getEmploye().getIdemploye();
        System.out.println("Utilisateur : " + utilisateur.getEmploye().getNoms());
        if (utilisateur != null) {

            List<Courrier> courriersTransmis = courrierDAO.getCourriersOday(idemploye);
            request.setAttribute("courriers", courriersTransmis);

            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idcourrier = 0;
                int a=0;
                int b=0; 
                int c=0 ;
                Courrier courrier = null;
                try {
                    idcourrier = Integer.parseInt(id);
                    courrier = courrierDAO.get(idcourrier);
                    if (courrier.getCourrierProcedureEnCourses() != null && courrier.getCourrierProcedureEnCourses().iterator().hasNext()) {
                        CourrierProcedureEnCours courrierProcedureEnCours = courrierProcedureEnCoursDAO.get(courrier.getCourrierProcedureEnCourses().iterator().next().getIdcourrierProcedureEnCours());
                        for(CourrierResponsable cr : courrierProcedureEnCours.getCourrierResponsables()){
                            if(cr.getStatut()!=null && cr.getStatut().equals("Terminé")){
                                a++;
                            }
                            c++;
                        }
                        request.setAttribute("a", a);
                        request.setAttribute("c", c);
                    }
                    List<CourrierResponsableCommentaire> commentaires = courrierResponsableCommentaireDAO.getall(courrier);
                    request.setAttribute("commentaires", commentaires);
                } catch (Exception e) {
                    //response.sendRedirect("courrier#/prise");*
                    e.printStackTrace();
                }
                List<Employe> employes = employeService.getAll();
                List<Structure> structures = structureService.getAll();
                request.setAttribute("structures", structures);
                request.setAttribute("employes", employes);

                request.setAttribute("utilisateur", utilisateur);
                request.setAttribute("courrier", courrier);
                if (courrier.getCorrespondant() != null && courrier.getCorrespondant().getEntreprisePartenaire() != null) {
                    EntreprisePartenaire e = entreprisePartenaireDAO.get(courrier.getCorrespondant().getEntreprisePartenaire().getIdentreprisePartenaire());
                    request.setAttribute("entreprise", e);
                    List<Courrier> courriers_e = new ArrayList<Courrier>();
                    List<Courrier> courriers_traites = new ArrayList<Courrier>();
                    List<Courrier> courriers_non_traites = new ArrayList<Courrier>();
                    for (Correspondant corresp : e.getCorrespondants()) {
                        for (Courrier crr : corresp.getCourriers()) {
                            if (crr.getStatut().equals("Traité") || crr.getStatut().equals("Archivé")) {
                                courriers_traites.add(crr);
                            } else {
                                courriers_non_traites.add(crr);
                            }
                            courriers_e.add(crr);
                        }
                    }
                    System.out.println("Courriers de l'entreprise : " + courriers_e.size());
                    System.out.println("Courriers traités : " + courriers_traites.size());
                    System.out.println("Courriers non traités : " + courriers_non_traites.size());
                    request.setAttribute("courriers_entreprise", courriers_e);
                    request.setAttribute("courriers_traites", courriers_traites);
                    request.setAttribute("courriers_non_traites", courriers_non_traites);
                }
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/prise_id_1.jsp").forward(request, response);
            } else {
                this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/courrier/prise.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("index.htm");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("terminer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idcourrier = Integer.parseInt(id);
                CourrierResponsable courrierResponsable = courrierResponsableDAO.get(idcourrier);
                courrierResponsable.setStatut("Terminé");
                if (courrierResponsableDAO.modifier(courrierResponsable)) {
                    Courrier courrier = courrierDAO.get(courrierResponsable.getCourrier().getIdcourrier());
                    courrier.setStatut("Traité");
                    courrierDAO.modifier(courrier);
                } else {

                }
            }
            response.sendRedirect("courrier#/prise");
        }
        if (action != null && !action.isEmpty() && action.equals("terminerTache")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int i = Integer.parseInt(id);
                CourrierResponsable courrierResponsable = courrierResponsableDAO.get(i);
                courrierResponsable.setStatut("Terminé");
                if (courrierResponsableDAO.modifier(courrierResponsable)) {
                    CourrierResponsable courrierResponsableSuivant = courrierResponsableDAO.getSuivant(courrierResponsable);
                    if (courrierResponsableSuivant != null) {

                        courrierResponsableSuivant.setStatut("En cours");
                        courrierResponsableSuivant.setDateRecuperation(new Date());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(new Date());
                        cal.add(Calendar.DATE, courrierResponsableSuivant.getCourrierProcedureTache().getDuree());
                        Date debut = cal.getTime();
                        courrierResponsableSuivant.setDateLimite(debut);
                        courrierResponsableDAO.modifier(courrierResponsableSuivant);
                    } else {
                        System.out.println("Le courrier suivant est nul");
                        Courrier c = courrierResponsable.getCourrier();
                        c.setStatut("Traité");
                        courrierDAO.modifier(c);
                    }
                    System.out.println("Le courrierResponsable a été modifié avec succès");

                } else {
                    System.out.println("Erreur de modification du courrierResponsable");

                }
            }
            response.sendRedirect("courrier#/prise");
        }
        if (action != null && !action.isEmpty() && action.equals("transmettre")) {
            System.out.println("Transmission init");
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idcourrier = Integer.parseInt(id);
                Courrier courrier = courrierDAO.get(idcourrier);
                for (CourrierResponsable cr : courrier.getCourrierResponsables()) {
                    cr.setStatut("Terminé");
                    courrierResponsableDAO.modifier(cr);
                    System.out.println("Modification succss");
                }
                String idemploye = request.getParameter("employe");
                System.out.println("Employe : " + idemploye);
                if (idemploye != null && !idemploye.isEmpty()) {
                    int i1 = Integer.parseInt(idemploye);
                    Employe employe = employeService.get(i1);
                    CourrierResponsable courrierResponsable = new CourrierResponsable();
                    courrierResponsable.setCourrier(courrier);
                    courrierResponsable.setEmploye(employe);
                    courrierResponsable.setStatut("En cours");
                    String approbation = request.getParameter("approbation");
                    courrierResponsable.setTypeApprobation(approbation);
                    String commentaire = request.getParameter("note");
                    courrierResponsable.setCommentaire(commentaire);
                    courrierResponsable.setDateRecuperation(new Date());
                    String date = request.getParameter("date_limite");
                    Date date_limite = null;
                    try {
                        date_limite = sdf.parse(date + " 23:59:59");
                    } catch (ParseException ex) {
                        date_limite = new Date();
                    }
                    courrierResponsable.setDateLimite(date_limite);
                    if (courrierResponsableDAO.ajouter(courrierResponsable)) {
                        System.out.println("Sauvegarde réussie !!! ");
                        courrier.setStatut("Transmis");
                        if (courrierDAO.modifier(courrier)) {
                            response.sendRedirect("courrier#/transmis/");
                        }

                    }
                }
            }
        }

    }

}
