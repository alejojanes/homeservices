package org.app.controller;

import io.quarkus.security.Authenticated;
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
    public List<org.app.entity.User> getAll(){
        return User.listAll();
    }

    @POST
    @Transactional
    public User create(User user){
        return userService.createUser(user);
    }
}
