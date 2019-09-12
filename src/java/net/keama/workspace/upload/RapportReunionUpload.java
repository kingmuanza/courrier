package net.keama.workspace.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItemStream;

public class RapportReunionUpload {
    public static String urlEntreprise = "entreprise";

    public static String processFile(String path, FileItemStream fileItemStream) {
        File f = new File(path + File.separator + "Rapports");
        if (!f.exists()) {
            f.mkdir();
        } else {

        }
        Random random = new Random();
        int randomNumber = random.nextInt(100);
        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = formatter.format(new Date());
        String nom = s.replace("-", "").replace(":", "").replace(" ", "")+randomNumber+"_"+fileItemStream.getName();
        File savedFile = new File(f.getAbsolutePath() + File.separator + nom);
        System.out.println("Chemin : " + f.getAbsolutePath() + File.separator + nom);
        System.out.println("Nom du fichier : " + nom);
        System.out.println("Muanza kangudie : " + f.getName());
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(savedFile);
            InputStream inputStream = fileItemStream.openStream();
            int x = 0 ;
            byte[] b = new byte[1024];
            while((x=inputStream.read(b))!=-1){
                fileOutputStream.write(b, 0, x);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            return nom ;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean processFileLogo(String path, FileItemStream fileItemStream) {
        File f = new File(path + File.separator + "UploadedImages");
        if (!f.exists()) {
            f.mkdir();
        } else {

        }
        File savedFile = new File(f.getAbsolutePath() + File.separator + "logo.jpg");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(savedFile);
            InputStream inputStream = fileItemStream.openStream();
            int x = 0;
            byte[] b = new byte[1024];
            while ((x = inputStream.read(b)) != -1) {
                fileOutputStream.write(b, 0, x);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
