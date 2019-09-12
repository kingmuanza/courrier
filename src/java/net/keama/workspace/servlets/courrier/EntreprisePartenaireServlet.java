package net.keama.workspace.servlets.courrier;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.EntreprisePartenaireDAO;
import net.keama.workspace.entity.EntreprisePartenaire;

@WebServlet(name="EntreprisePartenaireServlet", urlPatterns={"/EntreprisePartenaireServlet"})
public class EntreprisePartenaireServlet extends HttpServlet {

    EntreprisePartenaireDAO entreprisePartenaireDAO = new EntreprisePartenaireDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int identity = Integer.parseInt(id);
            
            request.setAttribute("attribut", "attribut");
        }
        request.setAttribute("attributs", "attributs");

        //Récupération des notification
        if(httpSession.getAttribute("creation")!=null){
            request.setAttribute("creation", (boolean) httpSession.getAttribute("creation"));
            httpSession.removeAttribute("creation");
        }
        if(httpSession.getAttribute("modification")!=null){
            request.setAttribute("modification", (boolean) httpSession.getAttribute("modification"));
            httpSession.removeAttribute("modification");
        }
        if(httpSession.getAttribute("suppression")!=null){
            request.setAttribute("suppression", (boolean) httpSession.getAttribute("suppression"));
            httpSession.removeAttribute("suppression");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/EntreprisePartenaireServlet.jsp").forward( request, response );
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession httpSession = request.getSession();
        
        //Récupération de l'action
        String action = request.getParameter("action");
        if (action!=null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if(id!=null && !id.isEmpty()){
                if(true){
                    httpSession.setAttribute("suppression", true);
                }else{
                    httpSession.setAttribute("suppression", false);
                }
            }
        }else{
            String id = request.getParameter("id");
            //Paramètres
            String nom = request.getParameter("nom");
            String adresse = request.getParameter("adresse");
            String tel = request.getParameter("tel");
            String email = request.getParameter("email");
            //Fin Paramètres
            EntreprisePartenaire entreprisePartenaire = null ;
            if(id!=null && !id.isEmpty()){
                if(true){
                    httpSession.setAttribute("modification", true);
                }else{
                    httpSession.setAttribute("modification", false);
                }
            }else{
                entreprisePartenaire = new EntreprisePartenaire();
                entreprisePartenaire.setAdresse(adresse);
                entreprisePartenaire.setMail(email);
                entreprisePartenaire.setNom(nom);
                entreprisePartenaire.setTel(tel);
                if(entreprisePartenaireDAO.ajouter(entreprisePartenaire)){
                    httpSession.setAttribute("creation", true);
                    try (PrintWriter out = response.getWriter()) {
                        out.println(entreprisePartenaire.getIdentreprisePartenaire());
                        System.out.println("ID du correspondant : "+entreprisePartenaire.getIdentreprisePartenaire());
                    }
                }else{
                    httpSession.setAttribute("creation", false);
                }
            }
        }
        
        response.sendRedirect("EntreprisePartenaireServlet");
    }


}
