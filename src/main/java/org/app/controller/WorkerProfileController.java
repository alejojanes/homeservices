package org.app.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.app.dto.WorkerProfileRequest;
import org.app.entity.WorkerProfile;
import org.app.service.WorkerProfileService;

import java.util.List;

@Path("/workers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WorkerProfileController {

    @Inject
    WorkerProfileService workerService;

    // ✅ Obtener todos los workers
    @GET
    public List<WorkerProfile> getAll(){
        return workerService.getAll();
    }

    // ✅ Obtener por ID
    @GET
    @Path("/{id}")
    public WorkerProfile getById(@PathParam("id") Long id){
        return workerService.getById(id);
    }

    // ✅ Crear worker profile (forma simple con DTO)
    @POST
    public WorkerProfile create(WorkerProfileRequest request){
        return workerService.create(request);
    }

    // ✅ Obtener worker por userId
    @GET
    @Path("/user/{userId}")
    public WorkerProfile getByUser(@PathParam("userId") Long userId){
        return workerService.getByUserId(userId);
    }

    // ✅ Eliminar worker
    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Long id){
        workerService.delete(id);
    }
}