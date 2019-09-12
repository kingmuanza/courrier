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
import net.keama.workspace.dao.EmployeDAO;
import net.keama.workspace.dao.MessageDAO;
import net.keama.workspace.dao.PosteDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Employe;
import net.keama.workspace.entity.Message;
import net.keama.workspace.entity.Poste;
import net.keama.workspace.entity.Utilisateur;

@WebServlet(name = "MessagerieServlet", urlPatterns = {"/message"})
public class MessagerieServlet extends HttpServlet {

    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
    MessageDAO messageDAO = new MessageDAO();
    EmployeDAO employeDAO = new EmployeDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());
        int i = utilisateur.getEmploye().getPoste().getIdposte();
        int j = utilisateur.getEmploye().getIdemploye();
        
        PosteDAO posteDAO = new PosteDAO();
        
        request.setAttribute("postes", posteDAO.getFilsAll(1));

        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            int identity = Integer.parseInt(id);
            request.setAttribute("recepteur", employeDAO.get(identity));
            request.setAttribute("messages", messageDAO.getall(identity, j));
            
        }
        request.setAttribute("utilisateur", utilisateur);
        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/message.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        HttpSession httpSession = request.getSession();

        //Récupération de l'action
        String action = request.getParameter("action");
        if (action != null && !action.isEmpty() && action.equals("supprimer")) {
            String id = request.getParameter("id");
            if (id != null && !id.isEmpty()) {
                if (true) {
                    httpSession.setAttribute("suppression", true);
                } else {
                    httpSession.setAttribute("suppression", false);
                }
            }
        } else {
            String id = request.getParameter("id");
            //Paramètres

            //Fin Paramètres
            if (id != null && !id.isEmpty()) {
                
            } else {
                String m = request.getParameter("message");
                System.out.println("Message : "+m);
                String emetteur = request.getParameter("emetteur");
                System.out.println("emeteur : "+emetteur);
                String recepteur = request.getParameter("recepteur");
                System.out.println("recepteur : "+recepteur);
                
                Employe e = employeDAO.get(Integer.parseInt(emetteur));
                Employe r = employeDAO.get(Integer.parseInt(recepteur));
                
                Message message = new Message();
                message.setEmployeByIdemetteur(e);
                message.setEmployeByIdrecepteur(r);
                message.setMessage(m);
                message.setDateEnvoi(new Date());
                messageDAO.ajouter(message);
                response.sendRedirect("message?id="+recepteur);
            }
        }

        
    }

}
