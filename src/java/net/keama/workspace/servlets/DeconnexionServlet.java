package net.keama.workspace.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name="DeconnexionServlet", urlPatterns={"/deconnexion"})
public class DeconnexionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("utilisateur", null);

        response.sendRedirect("index.htm");
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        response.sendRedirect("start");
    }


}
