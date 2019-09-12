package net.keama.workspace.servlets.tache;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.TacheDAO;
import net.keama.workspace.dao.TacheDocumentDAO;
import net.keama.workspace.dao.UtilisateurDAO;
import net.keama.workspace.entity.Tache;
import net.keama.workspace.entity.TacheDocument;
import net.keama.workspace.entity.Utilisateur;
import net.keama.workspace.upload.TacheDocumentUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

@WebServlet(name="DocumentTacheServlet", urlPatterns={"/DocumentTacheServlet"})
public class DocumentTacheServlet extends HttpServlet {

    TacheDAO tacheDAO = new TacheDAO();
    TacheDocumentDAO tacheDocumentDAO = new TacheDocumentDAO();
    UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        HttpSession httpSession = request.getSession();
        Utilisateur u = null;
        Utilisateur utilisateur = null;
        u = (Utilisateur) httpSession.getAttribute("utilisateur");
        utilisateur = utilisateurDAO.get(u.getIdutilisateur());

        System.out.println("Muanza Multipart : " + isMultipart);
        boolean b = false;

        if (isMultipart) {
            ServletFileUpload servletFileUpload = new ServletFileUpload();
            System.out.println("Début !!!");
            Tache tache = null;
            TacheDocument tacheDocument = new TacheDocument();
            try {
                FileItemIterator itr = servletFileUpload.getItemIterator(request);
                while (itr.hasNext()) {
                    FileItemStream fileItemStream = itr.next();
                    if (fileItemStream.isFormField()) {
                        System.out.println("Formulaire normal !!!");
                        System.out.println("Champ : " + fileItemStream.getFieldName());
                        if (fileItemStream.getFieldName().equals("tache")) {
                            InputStream stream = fileItemStream.openStream();
                            String value = Streams.asString(stream);
                            System.out.println(fileItemStream.getFieldName() + " : " + value);
                            if (value != null && !value.isEmpty()) {
                                int i = Integer.parseInt(value);
                                tache = tacheDAO.get(i);
                                tacheDocument.setTache(tache);
                                tacheDocument.setDateEnregistrement(new Date());
                                tacheDocument.setEmploye(utilisateur.getEmploye());
                            }
                        }
                        if (fileItemStream.getFieldName().equals("nom")) {
                            InputStream stream2 = fileItemStream.openStream();
                            String value = Streams.asString(stream2, "UTF-8");
                            System.out.println(fileItemStream.getFieldName() + " : " + value);
                            if (value != null && !value.isEmpty()) {
                                String nom = Charset.forName("UTF-8").encode(value).toString();
                                tacheDocument.setNom(value);
                            }
                        }
                    } else {
                        System.out.println("On entre ds le fichier !!!!");
                        String path = getServletContext().getRealPath("/");
                        String chemin = TacheDocumentUpload.processFile(path, fileItemStream);
                        if (chemin != null) {
                            tacheDocument.setChemin(chemin);
                            b = true;
                        } else {
                            System.out.println("NDEM !!!!");
                        }
                        System.out.println("Fin !!!");
                    }
                }
                if (b) {
                    if (tacheDocumentDAO.ajouter(tacheDocument)) {
                        httpSession.setAttribute("ajoutRapport", true);
                    } else {
                        httpSession.setAttribute("ajoutRapport", false);
                    }
                } else {
                    httpSession.setAttribute("ajoutRapport", false);
                }
                response.sendRedirect("tache#/avenir/light/" + tache.getIdtache());

            } catch (FileUploadException ex) {
                Logger.getLogger(DocumentTacheServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.out.println("Muanza Multipart : " + isMultipart);
            String action = request.getParameter("action");
            System.out.println("Action : " + action);
            if (action != null && !action.isEmpty() && action.equals("supprimer")) {
                String id = request.getParameter("id");
                System.out.println("ID : " + id);
                if (id != null && !id.isEmpty()) {
                    int i = Integer.parseInt(id);
                    TacheDocument tacheDocument = tacheDocumentDAO.get(i);
                    int idtache = tacheDocument.getTache().getIdtache();
                    if (tacheDocumentDAO.supprimer(tacheDocument)) {
                        System.out.println("Suppression effectuée !!");
                        response.sendRedirect("tache#/avenir/light/" + idtache);
                    }
                }
            }
        }

    }


}
