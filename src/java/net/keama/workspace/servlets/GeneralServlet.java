package net.keama.workspace.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;

@WebServlet(name="GeneralServlet", urlPatterns={"/general"})
public class GeneralServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        request.setAttribute("attribut", "attribut");
        request.setAttribute("attributs", "attributs");

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/general.jsp").forward( request, response );
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        String parametre = request.getParameter("parametre");

        response.sendRedirect("/general");
    }


}
