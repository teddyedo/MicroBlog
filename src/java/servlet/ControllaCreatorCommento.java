/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.PostJpaController;
import DAO.UtenteJpaController;
import entity.Post;
import entity.Utente;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ControllaCreatorCommento extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {

            String username = (String) session.getAttribute("username");

            Utente u = UtenteJpaController.findUtentebyUsername(username);

            List<Post> listaPost = PostJpaController.findAll();
            Post post = null;

            for (Post p : listaPost) {
                if (request.getParameter(String.valueOf(p.getId())) != null) {
                    post = p;
                    break;
                }
            }
            
            session.setAttribute("post", post);
            request.getRequestDispatcher("creaCommento.html").include(request, response);

        } else {
            //porta alla pagina di errore - utente non autorizzato
            request.getRequestDispatcher("utenteNonAutorizzato.html").include(request, response);
        }

    }

}
