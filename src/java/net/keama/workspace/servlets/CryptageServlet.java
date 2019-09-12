package net.keama.workspace.servlets;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "CryptageServlet", urlPatterns = {"/CryptageServlet"})
public class CryptageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String monMessage = "MUANZA";

        calculerValeurDeHachage("MD2", monMessage);

        calculerValeurDeHachage("MD5", monMessage);

        calculerValeurDeHachage("SHA-1", monMessage);

        calculerValeurDeHachage("SHA-256", monMessage);

        calculerValeurDeHachage("SHA-384", monMessage);

        calculerValeurDeHachage("SHA-512", monMessage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public static byte[] calculerValeurDeHachage(String algorithme, String monMessage) {

        byte[] digest = null;

        try {

            MessageDigest sha = MessageDigest.getInstance(algorithme);

            digest = sha.digest(monMessage.getBytes());

            System.out.println("algorithme : " + algorithme);

            System.out.println(bytesToHex(digest));

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        }

        return digest;

    }

    public static String bytesToHex(byte[] b) {

        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        StringBuffer buffer = new StringBuffer();

        for (int j = 0; j < b.length; j++) {

            buffer.append(hexDigits[(b[j] >> 4) & 0x0f]);
            buffer.append(hexDigits[b[j] & 0x0f]);

        }

        return buffer.toString();

    }

}
