package net.keama.workspace.servlets;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.entity.Reunion;

@WebServlet(name="Select2Servlet", urlPatterns={"/Select2Servlet"})
public class Select2Servlet extends HttpServlet {

    ReunionDAO reunionDAO = new ReunionDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        List<Reunion> reunions = reunionDAO.getall();
        request.setAttribute("reunions", reunions);

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/Select2Servlet.jsp").forward( request, response );
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
