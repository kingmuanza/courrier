package net.keama.workspace.servlets.parametres;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.dao.PosteDAO;
import net.keama.workspace.dao.SiteDAO;
import net.keama.workspace.dao.StructureDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Poste;
import net.keama.workspace.service.EntrepriseService;

@WebServlet(name = "EmployeServlet", urlPatterns = {"/EmployeServlet"})
public class EmployeServlet extends HttpServlet {

    EmployeDAO employeDAO = new EmployeDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PosteDAO posteDAO = new PosteDAO();
        SiteDAO siteDAO = new SiteDAO();
        StructureDAO structureDAO = new StructureDAO();
        EntrepriseService entrepriseService = new EntrepriseService();
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            request.setAttribute("employe", employeDAO.get(Integer.parseInt(id)));
            request.setAttribute("postes", posteDAO.getFilsAll(1));
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/employe_id.jsp").forward(request, response);
        } else {
            request.setAttribute("postes", posteDAO.getFilsAll(1));
            request.setAttribute("sites", siteDAO.getall());
            request.setAttribute("structures", structureDAO.getFilsAll(1));
            request.setAttribute("entreprise", entrepriseService.get(1));
            this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/parametres/employe.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PosteDAO posteDAO = new PosteDAO();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                if (true) {
                    
                } else {
                    
                }
            }
        } else {
            String id = request.getParameter("id");
            System.out.println("ID de l'employé : "+id);
            //Paramètres
            String idposte = request.getParameter("poste");
            int id_poste = 0 ;
            Poste poste = null ;
            if(idposte!=null && !idposte.isEmpty()){
                id_poste = Integer.parseInt(idposte);
                poste = posteDAO.get(id_poste);
            }
            String noms = request.getParameter("noms");
            String prenoms = request.getParameter("prenoms");
            String datenaiss = request.getParameter("datenaiss");
            Date date_naiss = null ;
            if(datenaiss!=null && !datenaiss.isEmpty()){
                try {
                    date_naiss = sdf.parse(datenaiss);
                } catch (ParseException ex) {
                    Logger.getLogger(EmployeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String lieunaiss = request.getParameter("lieunaiss");
            String cni = request.getParameter("cni");
            String dateAccession = request.getParameter("dateAccession");
            Date date_accession = null ;
            if(dateAccession!=null && !dateAccession.isEmpty()){
                try {
                    date_accession = sdf.parse(dateAccession);
                } catch (ParseException ex) {
                    Logger.getLogger(EmployeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            String matricule = request.getParameter("matricule");
            String sexe = request.getParameter("sexe");
            boolean isFemme = false ;
            if(sexe.equals("1")){
                isFemme = true ;
            }
            String tel1 = request.getParameter("tel1");
            String tel2 = request.getParameter("tel2");
            String email1 = request.getParameter("email1");
            String email2 = request.getParameter("email2");

            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                
                int i = Integer.parseInt(id);
                Employe employe = employeDAO.get(i);
                employe.setCni(cni);
                employe.setDateAccession(date_accession);
                employe.setDatenaiss(date_naiss);
                employe.setEmail1(email1);
                employe.setEmail2(email2);
                employe.setLieunaiss(lieunaiss);
                employe.setMatricule(matricule);
                employe.setNoms(noms);
                employe.setPoste(poste);
                employe.setPrenoms(prenoms);
                employe.setSexe(isFemme);
                employe.setTel1(tel1);
                employe.setTel2(tel2);
                if (employeDAO.modifier(employe)) {
                    response.sendRedirect("parametres#/employe/"+employe.getIdemploye());
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else {
                Employe employe = new Employe();
                employe.setCni(cni);
                employe.setDateAccession(date_accession);
                employe.setDatenaiss(date_naiss);
                employe.setEmail1(email1);
                employe.setEmail2(email2);
                employe.setLieunaiss(lieunaiss);
                employe.setMatricule(matricule);
                employe.setNoms(noms);
                employe.setPoste(poste);
                employe.setPrenoms(prenoms);
                employe.setSexe(isFemme);
                employe.setTel1(tel1);
                employe.setTel2(tel2);
                if (employeDAO.ajouter(employe)) {
                    response.sendRedirect("parametres#/employe/"+employe.getIdemploye());
                } else {
                    httpSession.setAttribute("creation", false);
                }
            }
        }

        
    }

}
