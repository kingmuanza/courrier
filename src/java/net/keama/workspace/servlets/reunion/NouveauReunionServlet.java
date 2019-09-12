package net.keama.workspace.servlets.reunion;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.ReunionEmployeDAO;
import net.keama.workspace.dao.SalleReunionDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.SalleReunion;

@WebServlet(name = "NouveauReunionServlet", urlPatterns = {"/NouveauReunionServlet"})
public class NouveauReunionServlet extends HttpServlet {

    EmployeDAO employeDAO = new EmployeDAO();
    ReunionDAO reunionDAO = new ReunionDAO();
    ReunionEmployeDAO reunionEmployeDAO = new ReunionEmployeDAO();
    SalleReunionDAO salleReunionDAO = new SalleReunionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm");

        HttpSession httpSession = request.getSession();
        Reunion reunion = null;
        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                if (true) {
                    httpSession.setAttribute("suppression", true);
                } else {
                    httpSession.setAttribute("suppression", false);
                }
            }
        } else {
            String id = request.getParameter("id");
            //Paramètres
            String employe = request.getParameter("employe");

            int idemploye = Integer.parseInt(employe);
            Employe e = employeDAO.get(idemploye);
            String date_reunion = request.getParameter("date_reunion");
            String heure_debut = request.getParameter("debut");
            String heure_fin = request.getParameter("fin");
            Date dateDebut = null;
            Date dateFin = null;
            try {
                dateDebut = sdf.parse(date_reunion + " " + heure_debut);
            } catch (ParseException ex) {
                Logger.getLogger(NouveauReunionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String date_fin = request.getParameter("date_fin");
            try {
                dateFin = sdf.parse(date_reunion + " " + heure_fin);
            } catch (ParseException ex) {
                Logger.getLogger(NouveauReunionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            String titre = request.getParameter("titre");
            String description = request.getParameter("description");
            String statut = request.getParameter("statut");
            String salle = request.getParameter("salle");
            int i = Integer.parseInt(salle);
            SalleReunion salleReunion = salleReunionDAO.get(i);

            reunion = new Reunion();
            reunion.setDateDebut(dateDebut);
            reunion.setDateFin(dateFin);
            reunion.setDescription(description);
            reunion.setEmploye(e);

            Random random = new Random();
            int randomNumber = random.nextInt(100);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = formatter.format(new Date());
            String nom = s.replace("-", "/").replace(":", "").replace(" ", "/") + randomNumber;

            reunion.setRef("REUNION/" + nom);
            reunion.setStatut("A venir");
            reunion.setTitre(titre);
            reunion.setSalleReunion(salleReunion);
            reunion.setDateEnregistrement(new Date());

            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                int j = Integer.parseInt(id);
                Reunion reunion2 = reunionDAO.get(j);
                reunion2.setDateDebut(dateDebut);
                reunion2.setDateFin(dateFin);
                reunion2.setDescription(description);
                reunion2.setEmploye(e);
                reunion2.setStatut("A venir");
                reunion2.setTitre(titre);
                reunion2.setSalleReunion(salleReunion);
                reunion2.setDateEnregistrement(new Date());
                reunion2.setReunionEmployes(null);
                if (reunionDAO.modifier(reunion2)) {
                    
                    String[] employes = request.getParameterValues("employes");
                    for (String re : employes) {
                        int idemp = Integer.parseInt(re);
                        Employe em = employeDAO.get(idemp);
                        System.out.println("Employe : " + em.getNoms());
                        Reunion r = reunionDAO.get(reunion2.getIdreunion());
                        ReunionEmploye reunionEmploye = new ReunionEmploye();
                        reunionEmploye.setEmploye(em);
                        reunionEmploye.setReunion(reunion2);
                        reunionEmploye.setStatut("Envoyé");
                        if (reunionEmployeDAO.ajouter(reunionEmploye)) {
                            System.out.println("Enregistrement de l'employe !!!");
                        }
                    }
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else if (reunionDAO.ajouter(reunion)) {
                String[] employes = request.getParameterValues("employes");
                for (String re : employes) {
                    int idemp = Integer.parseInt(re);
                    Employe em = employeDAO.get(idemp);
                    System.out.println("Employe : " + em.getNoms());
                    Reunion r = reunionDAO.get(reunion.getIdreunion());
                    ReunionEmploye reunionEmploye = new ReunionEmploye();
                    reunionEmploye.setEmploye(em);
                    reunionEmploye.setReunion(reunion);
                    reunionEmploye.setStatut("Envoyé");
                    if (reunionEmployeDAO.ajouter(reunionEmploye)) {
                        System.out.println("Enregistrement de l'employe !!!");
                    }
                }
                httpSession.setAttribute("creation", true);
            } else {
                httpSession.setAttribute("creation", false);
            }
        }

        response.sendRedirect("reunion#/semaine");
    }

}
