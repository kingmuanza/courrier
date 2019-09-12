package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierResponsableCommentaireDAO;
import net.keama.workspace.dao.CourrierResponsableCommentaireFichierDAO;
import net.keama.workspace.dao.CourrierResponsableDAO;
import net.keama.workspace.entity.CourrierResponsable;
import net.keama.workspace.entity.CourrierResponsableCommentaire;
import net.keama.workspace.entity.CourrierResponsableCommentaireFichier;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionRapport;
import net.keama.workspace.servlets.reunion.RapportReunionServlet;
import net.keama.workspace.upload.CommentaireCourrierUpload;
import net.keama.workspace.upload.RapportReunionUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

@WebServlet(name = "CommentaireCourrierServlet", urlPatterns = {"/CommentaireCourrierServlet"})
public class CommentaireCourrierServlet extends HttpServlet {

    CourrierResponsableDAO courrierResponsableDAO = new CourrierResponsableDAO();
    CourrierResponsableCommentaireDAO courrierResponsableCommentaireDAO = new CourrierResponsableCommentaireDAO();
    CourrierResponsableCommentaireFichierDAO courrierResponsableCommentaireFichierDAO = new CourrierResponsableCommentaireFichierDAO();

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
        if (httpSession.getAttribute("creation") != null) {
            request.setAttribute("creation", (boolean) httpSession.getAttribute("creation"));
            httpSession.removeAttribute("creation");
        }
        if (httpSession.getAttribute("modification") != null) {
            request.setAttribute("modification", (boolean) httpSession.getAttribute("modification"));
            httpSession.removeAttribute("modification");
        }
        if (httpSession.getAttribute("suppression") != null) {
            request.setAttribute("suppression", (boolean) httpSession.getAttribute("suppression"));
            httpSession.removeAttribute("suppression");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/CommentaireCourrierServlet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        HttpSession httpSession = request.getSession();

        System.out.println("Muanza Multipart : " + isMultipart);
        boolean a = false;
        boolean b = false;
        boolean c = false;
        int idcourrier = 0;

        if (isMultipart) {
            ServletFileUpload servletFileUpload = new ServletFileUpload();
            System.out.println("Début !!!");
            CourrierResponsable courrierResponsable = null;
            CourrierResponsableCommentaire courrierResponsableCommentaire = new CourrierResponsableCommentaire();
            CourrierResponsableCommentaireFichier courrierResponsableCommentaireFichier = new CourrierResponsableCommentaireFichier();
            try {
                FileItemIterator itr = servletFileUpload.getItemIterator(request);
                while (itr.hasNext()) {
                    FileItemStream fileItemStream = itr.next();
                    if (fileItemStream.isFormField()) {
                        System.out.println("Formulaire normal !!!");
                        System.out.println("Champ : " + fileItemStream.getFieldName());
                        if (fileItemStream.getFieldName().equals("courrierResponsable")) {
                            InputStream stream = fileItemStream.openStream();
                            String value = Streams.asString(stream);
                            System.out.println(fileItemStream.getFieldName() + " : " + value);
                            if (value != null && !value.isEmpty()) {
                                int i = Integer.parseInt(value);
                                courrierResponsable = courrierResponsableDAO.get(i);
                                idcourrier = courrierResponsable.getCourrier().getIdcourrier();
                                courrierResponsableCommentaire.setCourrierResponsable(courrierResponsable);
                                courrierResponsableCommentaire.setDateEnregistrement(new Date());
                                a = true;
                            }
                        }
                        if (fileItemStream.getFieldName().equals("commentaire")) {
                            InputStream stream2 = fileItemStream.openStream();
                            String value = Streams.asString(stream2, "UTF-8");
                            System.out.println(fileItemStream.getFieldName() + " : " + value);
                            if (value != null && !value.isEmpty()) {
                                courrierResponsableCommentaire.setCommentaire(value);
                                b = true;
                            }
                        }
                    } else {
                        System.out.println("On entre ds le fichier !!!!");
                        String path = getServletContext().getRealPath("/");
                        if(fileItemStream.getName()!=null && !fileItemStream.getName().isEmpty()){
                            System.out.println("Voici le nom du fichier envoyé : "+fileItemStream.getName());
                            String chemin = CommentaireCourrierUpload.processFile(path, fileItemStream);
                            if (chemin != null) {
                                courrierResponsableCommentaireFichier.setChemin(chemin);
                                c = true;
                            } else {
                                System.out.println("NDEM !!!!");
                            }
                            System.out.println("Fin !!!");
                        }
                    }
                }
                if (a && b) {
                    if (courrierResponsableCommentaireDAO.ajouter(courrierResponsableCommentaire)) {
                        courrierResponsableCommentaireFichier.setCourrierResponsableCommentaire(courrierResponsableCommentaire);
                        courrierResponsableCommentaireFichier.setDateEnregistrement(new Date());
                        courrierResponsableCommentaireFichier.setNom("Aucun");
                        if (c) {
                            courrierResponsableCommentaireFichierDAO.ajouter(courrierResponsableCommentaireFichier);
                        }
                    }
                }
                response.sendRedirect("courrier#/prise/" + idcourrier);

            } catch (FileUploadException ex) {
                Logger.getLogger(RapportReunionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

        }

    }

}
