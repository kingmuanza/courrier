package net.keama.workspace.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="WorkSpaceServlet", urlPatterns={"/WorkSpaceServlet"})
public class WorkSpaceServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        request.setAttribute("attribut", "attribut");
        request.setAttribute("attributs", "attributs");

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/WorkSpaceServlet.jsp").forward( request, response );
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        String parametre = request.getParameter("parametre");

        response.sendRedirect("WorkSpaceServlet");
    }


}
