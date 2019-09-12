package net.keama.workspace.servlets;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.text.SimpleDateFormat;
import javax.servlet.annotation.WebServlet;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

@WebServlet(name = "PDFtoImageServlet", urlPatterns = {"/PDFtoImageServlet"})
public class PDFtoImageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = getServletContext().getRealPath("/");
        File f = new File(path + File.separator + "PDF");
        if (!f.exists()) {
            f.mkdir();
        }
        String pdfFilename = "D:\\data.pdf";
        PDDocument document = PDDocument.load(new File(pdfFilename));
        PDFRenderer pdfRenderer = new PDFRenderer(document);
        for (int page = 0; page < document.getNumberOfPages(); ++page) {
            BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

            // suffix in filename will be used as the file format
//            if(ImageIOUtil.writeImage(bim, path + "PDF" + File.separator + "fichier-" + (page + 1) + ".png", 300)){
//                System.out.println("Fichier créé : "+ path + "PDF" + File.separator + "fichier-" + (page + 1) + ".png");
//            }
            
        }
        document.close();
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
                if (true) {
                    httpSession.setAttribute("modification", true);
                } else {
                    httpSession.setAttribute("modification", false);
                }
            } else if (true) {
                httpSession.setAttribute("creation", true);
            } else {
                httpSession.setAttribute("creation", false);
            }
        }

        response.sendRedirect("PDFtoImageServlet");
    }

}
