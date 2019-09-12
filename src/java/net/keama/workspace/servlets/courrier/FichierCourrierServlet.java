package net.keama.workspace.servlets.courrier;


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
import javax.servlet.http.HttpSession;
import net.keama.workspace.dao.CourrierDAO;
import net.keama.workspace.dao.CourrierFichierDAO;
import net.keama.workspace.entity.Courrier;
import net.keama.workspace.entity.CourrierFichier;
import net.keama.workspace.upload.FichierCourrierUpload;
import net.keama.workspace.upload.FileUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

@WebServlet(name="FichierCourrierServlet", urlPatterns={"/FichierCourrierServlet"})
public class FichierCourrierServlet extends HttpServlet {

    CourrierDAO courrierDAO = new CourrierDAO();
    CourrierFichierDAO courrierFichierDAO = new CourrierFichierDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request) ;
        HttpSession httpSession = request.getSession();
        
        System.out.println("Muanza Multipart : " + isMultipart);
        boolean b = false ;
        
        if(isMultipart){
            ServletFileUpload servletFileUpload = new ServletFileUpload();
            System.out.println("Début !!!");
            Courrier courrier = null ;
            CourrierFichier courrierFichier = new CourrierFichier();
            try {
                FileItemIterator itr = servletFileUpload.getItemIterator(request);
                while(itr.hasNext()){
                    FileItemStream fileItemStream = itr.next();
                    if(fileItemStream.isFormField()){
                        System.out.println("Formulaire normal !!!");
                        System.out.println("Champ : " + fileItemStream.getFieldName() );
                        InputStream stream = fileItemStream.openStream();
                        String value = Streams.asString(stream);
                        System.out.println(fileItemStream.getFieldName() +" : "+value );
                        if(value!=null && !value.isEmpty()){
                            int i = Integer.parseInt(value);
                            courrier = courrierDAO.get(i);
                            
                            courrierFichier.setCourrier(courrier);
                            courrierFichier.setDateEnr(new Date());
                            courrierFichier.setOrdre(1);
                            
                        }
                    }else{
                        System.out.println("On entre ds le fichier !!!!");
                        String path = getServletContext().getRealPath("/");
                        String chemin = FichierCourrierUpload.processFile(path, fileItemStream);
                        if(chemin!=null){
                            courrierFichier.setChemin(chemin);
                            b = true ;
                        }else{
                            System.out.println("NDEM !!!!");
                        }
                        System.out.println("Fin !!!");
                    }
                }
                if(b){
                    if(courrierFichierDAO.ajouter(courrierFichier)){
                        httpSession.setAttribute("ajoutFichier", true);
                    }else{
                        httpSession.setAttribute("ajoutFichier", false);
                    }
                }else{
                    httpSession.setAttribute("ajoutFichier", false);
                }
                response.sendRedirect("courrier#/creation/"+courrier.getIdcourrier());
                
            } catch (FileUploadException ex) {
                Logger.getLogger(FichierCourrierServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else{
            System.out.println("Muanza Multipart : " + isMultipart);
            String action = request.getParameter("action");
            System.out.println("Action : " + action);
            if(action!=null && !action.isEmpty() && action.equals("supprimer")){
                String id = request.getParameter("id");
                System.out.println("ID : " + id);
                if(id!=null && !id.isEmpty()){
                    int i = Integer.parseInt(id);
                    CourrierFichier courrierFichier = courrierFichierDAO.get(i);
                    int idcourrier = courrierFichier.getCourrier().getIdcourrier();
                    if(courrierFichierDAO.supprimer(courrierFichier)){
                        System.out.println("Suppression effectuée !!");
                        response.sendRedirect("courrier#/id/"+idcourrier);
                    }
                }
            }
        }
        
    }


}
