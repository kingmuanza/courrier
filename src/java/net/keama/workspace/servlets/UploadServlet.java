package net.keama.workspace.servlets;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.WebServlet;
import net.keama.workspace.upload.FileUpload;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@WebServlet(name="UploadServlet", urlPatterns={"/UploadServlet"})
public class UploadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        request.setAttribute("attribut", "attribut");
        request.setAttribute("attributs", "attributs");

        this.getServletContext().getRequestDispatcher("/WEB-INF/jsp/UploadServlet.jsp").forward( request, response );
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request) ;
        System.out.println("Muanza Multipart : " + isMultipart);
        if(isMultipart){
            ServletFileUpload servletFileUpload = new ServletFileUpload();
            System.out.println("Début !!!");
            try {
                FileItemIterator itr = servletFileUpload.getItemIterator(request);
                while(itr.hasNext()){
                    FileItemStream fileItemStream = itr.next();
                    if(fileItemStream.isFormField()){
                        System.out.println("Formulaire normal !!!");
                    }else{
                        String path = getServletContext().getRealPath("/");
                        if(FileUpload.processFileLogo(path, fileItemStream)){
                            System.out.println("Appamrammmtent tou sest tresss bien paseeee !!!!");
                        }else{
                            System.out.println("Ya eu un souci !!");
                        }
                    }
                }
            } catch (FileUploadException ex) {
                Logger.getLogger(UploadServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            
        }
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String id = request.getParameter("id");
        String parametre = request.getParameter("parametre");

        response.sendRedirect("parametres#/entreprise");
    }


}
