/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.UtenteJpaController;
import entity.Utente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Allari Edoardo
 *
 */
public class ControllaCreatorPost extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {

            String username = (String) session.getAttribute("username");

            Utente u = UtenteJpaController.findUtentebyUsername(username);

            if ("0".equals(u.getLivello())) {

                request.getRequestDispatcher("creaPost.html").include(request, response);

            } else {
                request.getRequestDispatcher("utenteNonAutorizzato.html").include(request, response);
            }

        } else {
            request.getRequestDispatcher("utenteNonAutorizzato.html").include(request, response);

        }

    }
}
