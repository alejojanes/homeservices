package org.app.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.app.entity.Status;
import org.app.entity.User;
import org.app.service.StatusService;

import java.util.List;

@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class StatusController {

    @Inject
    StatusService statusService;

    @GET
    public List<Status> getAll(){
        return statusService.getAllStatus();
    }

    @POST
    @Transactional
    public Status create(Status status){
        return statusService.create(status);
    }

}
