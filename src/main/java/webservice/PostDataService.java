/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import DAO.PostJpaController;
import entity.Post;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Allari Edoardo
 */
@Path("/posts")
public class PostDataService {

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Post> getPersone() {
        List<Post> Posts = new ArrayList<Post>();
        Posts.addAll(PostJpaController.findAll());
        return Posts;
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPersona(@PathParam("id") String personaID) {
        Post p = PostJpaController.findPost(Long.parseLong(personaID));
        return p;
    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public void newUser(Post p)
            throws IOException {
       
        Post post = new Post();
        post.setDataOra(p.getDataOra());
        post.setId(p.getId());
        post.setTesto(p.getTesto());
        post.setTitolo(p.getTitolo());
        post.setUtente(p.getUtente());
        
        PostJpaController.create(post);
        
    }

}
