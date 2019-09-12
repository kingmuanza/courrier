package net.keama.workspace.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.fileupload.FileItemStream;

public class FileUpload {
    
    public static String urlEntreprise="entreprise";
    
    public static boolean processFile(String path, FileItemStream fileItemStream){
        File f = new File(path+File.separator+"UploadedImages");
        if(!f.exists()){
            f.mkdir();
        }else{
            
        }
        File savedFile = new File(f.getAbsolutePath()+File.separator+fileItemStream.getName());
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
            return true ;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }
    
    public static boolean processFileLogo(String path, FileItemStream fileItemStream){
        File f = new File(path+File.separator+"UploadedImages");
        if(!f.exists()){
            f.mkdir();
        }else{
            
        }
        File savedFile = new File(f.getAbsolutePath()+File.separator+"logo.jpg");
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
            return true ;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }
}
