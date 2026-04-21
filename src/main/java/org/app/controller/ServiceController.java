package org.app.controller;

import io.quarkus.security.Authenticated;
import jakarta.ws.rs.core.Response;
import org.app.dto.ServiceRequest;
import org.app.dto.ServiceUpdate;
import org.app.entity.Service;
import org.app.service.ServiceService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.util.List;


@Path("/services")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ServiceController {

    @Inject
    ServiceService serviceService;

    @GET
    public List<Service> getAll(){
        return serviceService.getAll();
    }

    @GET
    @Path("/worker/{workerId}")
    public List<Service> getByWorker(@PathParam("workerId") Long workerId){
        return serviceService.getByWorker(workerId);
    }

    @GET
    @Path("/category/{categoryId}")
    public List<Service> getByCategory(@PathParam("categoryId") Long categoryId){
        return serviceService.getByCategory(categoryId);
    }

    @POST
    @Path("/worker/{workerId}/category/{categoryId}")
    public Service createByWorkerIdAndCategoryId(
            @PathParam("workerId") Long workerId,
            @PathParam("categoryId") Long categoryId,
            Service service){

        return serviceService.createByWorkerAndCategory(workerId, categoryId, service);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Service createService(ServiceRequest request) {

        return serviceService.create(request);
    }

    @PUT
    public Service update(ServiceUpdate serviceUpdate){

        return serviceService.update(serviceUpdate);
    }

}