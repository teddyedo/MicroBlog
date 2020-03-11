/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.CommentoJpaController;
import DAO.PostJpaController;
import DAO.UtenteJpaController;
import entity.Commento;
import entity.Post;
import entity.Utente;
import java.io.IOException;
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
public class CreaCommento extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String titolo = request.getParameter("titolo");
        String testo = request.getParameter("testo");

        Date dataOra = new Date();
        Commento c = new Commento();

        c.setDataOra(dataOra);
        c.setTesto(testo);
        c.setTitolo(titolo);

        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        Utente u = UtenteJpaController.findUtentebyUsername(username);

        c.setUtente(u);
        c.setPost((Post) session.getAttribute("post"));
        CommentoJpaController.create(c);

        //rimandare alla pagina dei post
        List<Post> listaPost = PostJpaController.findAll();

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("listaPost", listaPost);
        request.getRequestDispatcher("postList.jsp").forward(request, response);

    }

}
