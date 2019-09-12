package net.keama.workspace.servlets;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.InformationGeneraleDAO;

@WebServlet(name="TestServlet", urlPatterns={"/TestServlet"})
public class TestServlet extends HttpServlet {

    InformationGeneraleDAO informationGeneraleDAO = new InformationGeneraleDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
//        System.out.println(new Date());
        System.out.println("Nombre d'informations générales : " + informationGeneraleDAO.getNombre());
//        System.out.println(new Date());
//        System.out.println("##################################################");
//        System.out.println(new Date());
//        System.out.println("Nombre d'informations générales : " + informationGeneraleDAO.getall().size());
//        System.out.println(new Date());
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

            //Fin Paramètres

            if(id!=null && !id.isEmpty()){
                if(true){
                    httpSession.setAttribute("modification", true);
                }else{
                    httpSession.setAttribute("modification", false);
                }
            }else{
                if(true){
                    httpSession.setAttribute("creation", true);
                }else{
                    httpSession.setAttribute("creation", false);
                }
            }
        }
        
        response.sendRedirect("TestServlet");
    }


}
