package net.keama.workspace.servlets.parametres;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.EntrepriseDAO;
import net.keama.workspace.dao.SiteDAO;
import net.keama.workspace.entity.Site;

@WebServlet(name = "SiteServlet", urlPatterns = {"/SiteServlet"})
public class SiteServlet extends HttpServlet {

    SiteDAO siteDAO = new SiteDAO();
    EntrepriseDAO entrepriseDAO = new EntrepriseDAO();

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

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/SiteServlet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            System.out.println("ID du site : " + id);
            int i = Integer.parseInt(id);
            Site site = siteDAO.get(i);
            try {
                if (siteDAO.supprimer(site)) {

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            HttpSession httpSession = request.getSession();

            String id = request.getParameter("id");
            System.out.println("ID du site : " + id);
            String nom = request.getParameter("nom");
            System.out.println("nom du site : " + nom);
            String sigle = request.getParameter("sigle");
            System.out.println("sigle du site : " + sigle);
            String siege = request.getParameter("siege");
            System.out.println("siege du site : " + siege);

            Site site = new Site();
            if (id != null && !id.isEmpty()) {
                if (siege != null) {
                    System.out.println("le site est le siège");
                    for (Site s : siteDAO.getall()) {
                        s.setEstSiege(false);
                        siteDAO.modifier(s);
                    }
                    int i = Integer.parseInt(id);
                    site = siteDAO.get(i);
                    site.setNom(nom);
                    site.setSigle(sigle);
                    site.setEntreprise(entrepriseDAO.get(1));
                    site.setEstSiege(true);
                    siteDAO.modifier(site);
                }else{
                    int i = Integer.parseInt(id);
                    site = siteDAO.get(i);
                    site.setNom(nom);
                    site.setSigle(sigle);
                    site.setEntreprise(entrepriseDAO.get(1));
                    site.setEstSiege(true);
                    siteDAO.modifier(site);
                }

            } else {
                if (siege != null) {
                    System.out.println("le site est le siège");

                    for (Site s : siteDAO.getall()) {
                        s.setEstSiege(false);
                        siteDAO.modifier(s);
                    }
                    site.setEstSiege(true);
                }
                site.setNom(nom);
                site.setSigle(sigle);
                site.setEntreprise(entrepriseDAO.get(1));
                siteDAO.ajouter(site);
            }
        }

        response.sendRedirect("parametres#/entreprise");

    }

}
