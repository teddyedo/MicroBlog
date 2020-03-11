/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.PostJpaController;
import DAO.UtenteJpaController;
import com.google.common.base.Charsets; 
import com.google.common.hash.Hasher; 
import com.google.common.hash.Hashing;
import entity.Post;
import entity.Utente;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Allari Edoardo
 */
public class CreaPost extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titolo = request.getParameter("titolo");
        String testo = request.getParameter("testo");

        Date dataOra = new Date();
        Post p = new Post();

        p.setDataOra(dataOra);
        p.setTesto(testo);
        p.setTitolo(titolo);
        
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Utente u = UtenteJpaController.findUtentebyUsername(username);
        
        p.setUtente(u);
        PostJpaController.create(p);
        
        //rimandare alla pagina dei post
        List<Post> listaPost = PostJpaController.findAll();
        
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("listaPost", listaPost);
        request.getRequestDispatcher("postList.jsp").forward(request, response);
        

    }
}
