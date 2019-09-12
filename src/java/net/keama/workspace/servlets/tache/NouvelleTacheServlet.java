package net.keama.workspace.servlets.tache;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.dao.TacheEmployeDAO;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.TacheEmploye;
import net.keama.workspace.service.EmployeService;

@WebServlet(name="NouvelleTacheServlet", urlPatterns={"/NouvelleTacheServlet"})
public class NouvelleTacheServlet extends HttpServlet {

    EmployeService employeService = new EmployeService();
    TacheDAO tacheDAO = new TacheDAO();
    TacheEmployeDAO tacheEmployeDAO = new TacheEmployeDAO ();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int idtache = Integer.parseInt(id);
            Tache tache = tacheDAO.get(idtache);
            request.setAttribute("tache", tache);
        }

        List<Employe> employes = employeService.getAll();

        List<Tache> taches = tacheDAO.getall();
        List<Tache> tachesEntrants = new ArrayList<Tache>();
        if (taches != null && !taches.isEmpty()) {
            for (Tache c : taches) {
                if(true){
                    tachesEntrants.add(c);
                }
            }
        }

        request.setAttribute("employes", employes);
        request.setAttribute("taches", tachesEntrants);


        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/tache/nouveau.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                int idtache = Integer.parseInt(id);
                Tache tache = tacheDAO.get(idtache);
                if (tacheDAO.supprimer(tache)) {
                    System.out.println("Le tache a été supprimé avec succès");
                    httpSession.setAttribute("suppression", true);
                } else {
                    System.out.println("Erreur de suppression du tache");
                    httpSession.setAttribute("suppression", false);
                }
            }
            response.sendRedirect("tache#/tous");

        } else {
            //Récupération de l'ID
            String id = request.getParameter("id");
            //Paramètres
            String libelle = request.getParameter("libelle");
            System.out.println("libelle : " + libelle);
            String date_debut = request.getParameter("date_debut");
            System.out.println("date_debut : " + date_debut);
            Date dateDebut = null ;
            try {
                dateDebut = sdf.parse(date_debut);
            } catch (ParseException ex) {
                dateDebut = new Date();
            }
            String date_fin = request.getParameter("date_fin");
            System.out.println("date_fin : " + date_fin);
            Date dateFin = null ;
            try {
                dateFin = sdf.parse(date_fin);
            } catch (ParseException ex) {
                dateFin = new Date();
            }
            String date_emission = request.getParameter("date_emission");
            System.out.println("date_emission : " + date_emission);
            Date dateEmission = null ;
            try {
                dateEmission = sdf.parse(date_emission);
            } catch (ParseException ex) {
                dateEmission = new Date();
            }
            String description = request.getParameter("description");
            System.out.println("description : " + description);
            
            String empl = request.getParameter("employe");
            System.out.println("employe : " + empl);
            Employe employe = null ;
            if(empl!=null && !empl.isEmpty()){
                int i = Integer.parseInt(empl);
                employe = employeService.get(i);
            }
            
            String[] employes = request.getParameterValues("employes");
            System.out.println("employes : " + employes.length);


            Tache tache = null;
            if (id != null && !id.isEmpty()) {
                int idtache = Integer.parseInt(id);
                tache = tacheDAO.get(idtache);
                tache.setDateDebut(dateDebut);
                tache.setDateEmission(dateEmission);
                tache.setDateFin(dateFin);
                tache.setDescription(description);
                tache.setLibelle(libelle);
                tache.setEmployeByIdordonnateur(employe);
                if (tacheDAO.modifier(tache)) {
                    httpSession.setAttribute("modification", true);
                    if(tache.getTacheEmployes()!=null){
                        for(TacheEmploye te : tache.getTacheEmployes()){
                            tacheEmployeDAO.supprimer(te);
                        }
                    }
                    for(String e : employes){
                        int ie = Integer.parseInt(e);
                        Employe em = employeService.get(ie);
                        TacheEmploye te = new TacheEmploye();
                        te.setEmploye(em);
                        te.setTache(tache);
                        tacheEmployeDAO.ajouter(te);
                    }
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else {
                tache = new Tache();
                tache.setDateDebut(dateDebut);
                tache.setDateEmission(dateEmission);
                tache.setDateFin(dateFin);
                tache.setDescription(description);
                tache.setLibelle(libelle);
                tache.setEmployeByIdordonnateur(employe);
                tache.setTacheEmployes(null);
                if(tacheDAO.ajouter(tache)){
                    httpSession.setAttribute("creation", true);
                    for(String e : employes){
                        int ie = Integer.parseInt(e);
                        Employe em = employeService.get(ie);
                        TacheEmploye te = new TacheEmploye();
                        te.setEmploye(em);
                        te.setTache(tache);
                        tacheEmployeDAO.ajouter(te);
                    }
                }else{
                    httpSession.setAttribute("creation", false);
                }
            }
            response.sendRedirect("tache#");
        }

    }


}
