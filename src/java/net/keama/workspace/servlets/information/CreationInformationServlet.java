package net.keama.workspace.servlets.information;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.dao.InformationGeneraleDAO;
import net.keama.workspace.dao.InformationGeneraleEmployeDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.InformationGenerale;
import net.keama.workspace.entity.InformationGeneraleEmploye;

@WebServlet(name="CreationInformationServlet", urlPatterns={"/CreationInformationServlet"})
public class CreationInformationServlet extends HttpServlet {

    InformationGeneraleDAO informationGeneraleDAO = new InformationGeneraleDAO();
    InformationGeneraleEmployeDAO informationGeneraleEmployeDAO = new InformationGeneraleEmployeDAO();
    EmployeDAO employeDAO = new EmployeDAO();
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

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CreationInformationServlet.jsp").forward( request, response );
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
            String titre = request.getParameter("titre");
            String contenu = request.getParameter("contenu");
            String idemploye = request.getParameter("employe");
            Employe employe = null ;
            if(idemploye!=null && !idemploye.isEmpty()){
                int i = Integer.parseInt(idemploye);
                employe = employeDAO.get(i);
            }
            String[] employes = request.getParameterValues("employes");

            //Fin Paramètres
            InformationGenerale informationGenerale = null;
            if(id!=null && !id.isEmpty()){
                int i = Integer.parseInt(id);
                informationGenerale = informationGeneraleDAO.get(i);
                informationGenerale.setContenu(contenu);
                informationGenerale.setDateEnregistrement(new Date());
                informationGenerale.setEmploye(employe);
                informationGenerale.setTitre(titre);
                if(informationGeneraleDAO.modifier(informationGenerale)){
                    for(InformationGeneraleEmploye ige : informationGenerale.getInformationGeneraleEmployes()){
                        informationGeneraleDAO.supprimer(informationGenerale);
                    }
                    for(String empl : employes){
                        int j = Integer.parseInt(empl);
                        Employe e = employeDAO.get(j);
                        InformationGeneraleEmploye ige = new InformationGeneraleEmploye();
                        ige.setEmploye(employe);
                        ige.setInformationGenerale(informationGenerale);
                        ige.setStatut("Envoyé");
                        informationGeneraleEmployeDAO.ajouter(ige);
                    }
                    httpSession.setAttribute("modification", true);
                }else{
                    httpSession.setAttribute("modification", false);
                }
            }else{
                informationGenerale = new InformationGenerale();
                informationGenerale.setContenu(contenu);
                informationGenerale.setDateEnregistrement(new Date());
                informationGenerale.setEmploye(employe);
                informationGenerale.setTitre(titre);
                if(informationGeneraleDAO.ajouter(informationGenerale)){
                    httpSession.setAttribute("creation", true);
                    for(String empl : employes){
                        int i = Integer.parseInt(empl);
                        Employe e = employeDAO.get(i);
                        InformationGeneraleEmploye ige = new InformationGeneraleEmploye();
                        ige.setEmploye(e);
                        ige.setInformationGenerale(informationGenerale);
                        ige.setStatut("Envoyé");
                        informationGeneraleEmployeDAO.ajouter(ige);
                    }
                }else{
                    httpSession.setAttribute("creation", false);
                }
            }
        }
        
        response.sendRedirect("information#/tous/light");
    }


}
