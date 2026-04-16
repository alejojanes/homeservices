package org.app.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.app.entity.User;
import org.app.service.UserService;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public List<User> getAll(){
        return userService.getAllUsers();
    }

    @GET
    @Path("/email/{email}")
    public User getAll(@PathParam("email") String email){
        return userService.getUserByEmail(email);
    }

    @PUT
    public User updateUser(User user){
        return userService.updateUser(user);
    }

    @POST
    @Transactional
    public User create(User user){
        return userService.createUser(user);
    }
}
