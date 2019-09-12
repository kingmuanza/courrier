package net.keama.workspace.servlets.reunion;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionEmploye;
import net.keama.workspace.entity.Utilisateur;

@WebServlet(name="EmploiTempsServlet", urlPatterns={"/EmploiTempsServlet"})
public class EmploiTempsServlet extends HttpServlet {

    ReunionDAO reunionDAO = new ReunionDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        
        Date oday = new Date();
        Date hier = new Date();
        Date demain = new Date();
        Long t = demain.getTime();
        
        oday.setHours(0);
        oday.setMinutes(0);
        oday.setSeconds(0);
        
        hier.setHours(0);
        hier.setMinutes(0);
        hier.setSeconds(0);
        
        demain.setHours(0);
        demain.setMinutes(0);
        demain.setSeconds(0);
        
        
        hier.setDate(hier.getDate()-1);
        demain.setDate(demain.getDate()+1);
        
        System.out.println("Aujourdhui : " + oday);
        System.out.println("Hier : " + hier);
        System.out.println("Demain : " + demain);
        
        

        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());

        List<Reunion> reunions = reunionDAO.getall();
        List<Reunion> mesReunions = new ArrayList<Reunion>();
        List<Reunion> autreReunions = new ArrayList<Reunion>();
        
        if (reunions != null && !reunions.isEmpty() && utilisateur != null) {
            for (Reunion c : reunions) {
                if(utilisateur.getEmploye().getIdemploye()== c.getEmploye().getIdemploye() && c.getDateDebut().after(oday) && c.getDateDebut().before(demain)){
                    mesReunions.add(c);
                }
                System.out.println("test date : " + c.getDateDebut() +" / " +c.getDateDebut().after(oday));
            }
            System.out.println("mes réunions : " + mesReunions);
        }
        
        if (reunions != null && !reunions.isEmpty() && utilisateur != null) {
            for (Reunion c : reunions) {
                if(utilisateur.getEmploye().getIdemploye()!= c.getEmploye().getIdemploye() && c.getDateDebut().after(oday) && c.getDateDebut().before(demain)){
                    if(c.getReunionEmployes()!=null){
                        for(ReunionEmploye re : c.getReunionEmployes()){
                            if(re.getEmploye().getIdemploye()==utilisateur.getEmploye().getIdemploye()){
                                autreReunions.add(c);
                            }
                        }
                    }
                }
            }
            System.out.println("mes réunions : " + autreReunions);
        }
        
        request.setAttribute("autreReunions", autreReunions);
        request.setAttribute("mesReunions", mesReunions);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/reunion/emploi.jsp").forward( request, response );
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String[] ref = request.getParameterValues("ref");
        System.out.println("Contenu du select2");
        for(String s : ref){
            System.out.println(s);
        }
        System.out.println("Fin !! Contenu du select2");
        System.out.println("Contenu du select2 : "+ref);
        response.sendRedirect("courrier");
    }


}
