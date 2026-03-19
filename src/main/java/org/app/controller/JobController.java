package org.app.controller;

import io.quarkus.security.Authenticated;
import org.app.entity.Job;
import org.app.entity.JobStatus;
import org.app.service.JobService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/jobs")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class JobController {

    @Inject
    JobService jobService;

    @GET
    @Path("/user/{userId}")
    public List<Job> getByUser(@PathParam("userId") Long userId){
        return jobService.getByUser(userId);
    }

    @GET
    @Path("/worker/{workerId}")
    public List<Job> getByWorker(@PathParam("workerId") Long workerId){
        return jobService.getByWorker(workerId);
    }

    @POST
    @Path("/user/{userId}/worker/{workerId}/service/{serviceId}")
    public Job create(
            @PathParam("userId") Long userId,
            @PathParam("workerId") Long workerId,
            @PathParam("serviceId") Long serviceId,
            Job job){

        return jobService.create(userId, workerId, serviceId, job);
    }

    @PUT
    @Path("/{jobId}/status/{status}")
    public Job updateStatus(
            @PathParam("jobId") Long jobId,
            @PathParam("status") JobStatus status){

        return jobService.updateStatus(jobId, status);
    }
}