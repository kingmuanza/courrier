package net.keama.workspace.servlets.reunion;

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
import net.keama.workspace.dao.ReunionDAO;
import net.keama.workspace.dao.ReunionRapportDAO;
import net.keama.workspace.entity.Reunion;
import net.keama.workspace.entity.ReunionRapport;
import net.keama.workspace.upload.RapportReunionUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

@WebServlet(name = "RapportReunionServlet", urlPatterns = {"/RapportReunionServlet"})
public class RapportReunionServlet extends HttpServlet {

    ReunionDAO reunionDAO = new ReunionDAO();
    ReunionRapportDAO reunionRapportDAO = new ReunionRapportDAO();

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

        System.out.println("Muanza Multipart : " + isMultipart);
        boolean b = false;

        if (isMultipart) {
            ServletFileUpload servletFileUpload = new ServletFileUpload();
            System.out.println("Début !!!");
            Reunion reunion = null;
            ReunionRapport reunionRapport = new ReunionRapport();
            try {
                FileItemIterator itr = servletFileUpload.getItemIterator(request);
                while (itr.hasNext()) {
                    FileItemStream fileItemStream = itr.next();
                    if (fileItemStream.isFormField()) {
                        System.out.println("Formulaire normal !!!");
                        System.out.println("Champ : " + fileItemStream.getFieldName());
                        if (fileItemStream.getFieldName().equals("reunion")) {
                            InputStream stream = fileItemStream.openStream();
                            String value = Streams.asString(stream);
                            System.out.println(fileItemStream.getFieldName() + " : " + value);
                            if (value != null && !value.isEmpty()) {
                                int i = Integer.parseInt(value);
                                reunion = reunionDAO.get(i);
                                reunionRapport.setReunion(reunion);
                                reunionRapport.setDateEnregistrement(new Date());

                            }
                        }
                        if (fileItemStream.getFieldName().equals("nom")) {
                            InputStream stream2 = fileItemStream.openStream();
                            String value = Streams.asString(stream2, "UTF-8");
                            System.out.println(fileItemStream.getFieldName() + " : " + value);
                            if (value != null && !value.isEmpty()) {
                                reunionRapport.setNom(value);
                            }
                        }
                    } else {
                        System.out.println("On entre ds le fichier !!!!");
                        String path = getServletContext().getRealPath("/");
                        String chemin = RapportReunionUpload.processFile(path, fileItemStream);
                        if (chemin != null) {
                            reunionRapport.setChemin(chemin);
                            b = true;
                        } else {
                            System.out.println("NDEM !!!!");
                        }
                        System.out.println("Fin !!!");
                    }
                }
                if (b) {
                    if (reunionRapportDAO.ajouter(reunionRapport)) {
                        httpSession.setAttribute("ajoutRapport", true);
                    } else {
                        httpSession.setAttribute("ajoutRapport", false);
                    }
                } else {
                    httpSession.setAttribute("ajoutRapport", false);
                }
                response.sendRedirect("reunion#/id/" + reunion.getIdreunion());

            } catch (FileUploadException ex) {
                Logger.getLogger(RapportReunionServlet.class.getName()).log(Level.SEVERE, null, ex);
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
                    ReunionRapport reunionRapport = reunionRapportDAO.get(i);
                    int idreunion = reunionRapport.getReunion().getIdreunion();
                    if (reunionRapportDAO.supprimer(reunionRapport)) {
                        System.out.println("Suppression effectuée !!");
                        response.sendRedirect("reunion#/id/" + idreunion);
                    }
                }
            }
        }

    }

}
