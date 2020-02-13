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
public class LoginUtente extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utente u = UtenteJpaController.findUtentebyUsername(username);

        Hasher hasher = Hashing.sha256().newHasher();

        long passwordEncrypted = Long.parseLong(u.getPassword());
        String salt = u.getSALT();

        String newPassword = password + salt;

        hasher.putString(newPassword, Charsets.UTF_8);
        long sha256 = hasher.hash().asLong();

        if (sha256 == passwordEncrypted) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            //manda a pagina login succeded

            List<Post> listaPost = PostJpaController.findAll();

            request.setCharacterEncoding("UTF-8");
            request.setAttribute("listaPost", listaPost);
            request.getRequestDispatcher("postList.jsp").forward(request, response);

        } else {
            //pagina di errore login
        }

    }
}
