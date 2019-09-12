package net.keama.workspace.servlets.courrier;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierFichierDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierFichier;
import net.keama.workspace.upload.FichierCourrierUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

@WebServlet(name = "FichierCourrierServlet2", urlPatterns = {"/FichierCourrierServlet2"})
public class FichierCourrierServlet2 extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierFichierDAO courrierFichierDAO = new CourrierFichierDAO();
    Random random = new Random();

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

            Courrier courrier = new Courrier();
            CourrierFichier courrierFichier = null;
            Date dateEmission = new Date();
            Format formatter = new SimpleDateFormat("yyyy-MM-dd");
            String date_emission = formatter.format(dateEmission);
            int randomNumber = random.nextInt(100);
            int ordre = 1 ;

            if (courrierDAO.ajouter(courrier)) {
                courrier.setRef(date_emission.replace("-", "/") + "/" + courrier.getIdcourrier());
                courrier.setStatut("Entrant");
                if (courrierDAO.modifier(courrier)) {
                    System.out.println("Création du courrier : " + courrier.getRef());
                    int i = courrier.getIdcourrier();
                    try {
                        FileItemIterator itr = servletFileUpload.getItemIterator(request);
                        int j = 1;
                        while (itr.hasNext()) {
                            FileItemStream fileItemStream = itr.next();
                            if (fileItemStream.isFormField()) {

                            } else {
                                System.out.println("Type" + fileItemStream.getContentType());
                                System.out.println("Fichier : " + j);
                                courrierFichier = new CourrierFichier();
                                courrier = courrierDAO.get(i);
                                courrierFichier.setCourrier(courrier);
                                courrierFichier.setOrdre(ordre);
                                ordre++;
                                String path = getServletContext().getRealPath("/");
                                String chemin = FichierCourrierUpload.processFile(path, fileItemStream);
                                if (chemin != null) {
                                    courrierFichier.setChemin(chemin);
                                    b = true;
                                    if (courrierFichierDAO.ajouter(courrierFichier)) {
                                        System.out.println("Ajout du fichier avec succès");
                                    }
                                } else {
                                    System.out.println("NDEM !!!!");
                                }
                                j++;
                            }
                        }
                    } catch (FileUploadException ex) {
                        Logger.getLogger(FichierCourrierServlet2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            response.sendRedirect("courrier#/creation/" + courrier.getIdcourrier());
        }
        System.out.println("Fin !!!");
    }

}
