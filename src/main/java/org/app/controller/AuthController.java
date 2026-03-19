package org.app.controller;

import io.smallrye.jwt.build.Jwt;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.app.dto.LoginResponse;
import org.app.entity.User;
import org.app.repository.UserRepository;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    UserRepository userRepository;

    @POST
    @Path("/login")
    public Response login(User loginRequest){

        User user = userRepository.find("email", loginRequest.email).firstResult();

        if(user == null || !user.password.equals(loginRequest.password)){
            return Response.status(401).entity("Invalid credentials").build();
        }

        String accessToken = generateToken(user);
        String refreshToken = generateRefreshToken(user); // opcional pero recomendado

        LoginResponse response = new LoginResponse();
        response.access_token = accessToken;
        response.refresh_token = refreshToken;
        response.token_type = "Bearer";
        response.status = "success";

        return Response.ok(response).build();
    }

    private String generateToken(User user){
        return Jwt.issuer("home-service-app")
                .subject(user.id.toString())
                .claim("role", user.role)
                .expiresIn(3600)
                .sign();
    }

    private String generateRefreshToken(User user){
        return Jwt.issuer("home-service-app")
                .subject(user.id.toString())
                .claim("type", "refresh")
                .expiresIn(86400) // 24 horas
                .sign();
    }
}