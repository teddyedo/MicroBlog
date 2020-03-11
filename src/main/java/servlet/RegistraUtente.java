/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.UtenteJpaController;
import com.google.common.base.Charsets;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import entity.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
/**
 *
 * @author Allari Edoardo
 * 
 */

public class RegistraUtente extends HttpServlet {

 
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        
        Random random = new SecureRandom();
                
        byte[] SaltGeneration = new byte[16];
        random.nextBytes(SaltGeneration);
        
        String salt = DatatypeConverter.printBase64Binary(SaltGeneration);
        
        
        String passwordEncrypted = password + salt;
        
        Hasher hasher = Hashing.sha256().newHasher();
        hasher.putString(passwordEncrypted, Charsets.UTF_8);
        long sha256 = hasher.hash().asLong();
        
        Utente u = new Utente();
        
        u.setEmail(email);
        u.setUsername(username);
        u.setPassword(Long.toString(sha256));
        u.setSALT(salt);
        u.setLivello("1");

        
        UtenteJpaController.create(u);
        
        request.getRequestDispatcher("index.html").forward(request, response);
  
    }
}
