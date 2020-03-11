/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import DAO.PostJpaController;
import entity.Post;
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
public class stampaPost extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        
        List<Post> listaPost = PostJpaController.findAll();
        
        HttpSession session = request.getSession(false);

        request.setCharacterEncoding("UTF-8");
        request.setAttribute("listaPost", listaPost);
        request.getRequestDispatcher("postList.jsp").forward(request, response);
    }
}
