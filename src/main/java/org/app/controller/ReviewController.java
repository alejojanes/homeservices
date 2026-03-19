package org.app.controller;

import io.quarkus.security.Authenticated;
import org.app.entity.Review;
import org.app.service.ReviewService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/reviews")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class ReviewController {

    @Inject
    ReviewService reviewService;

    @GET
    @Path("/worker/{workerId}")
    public List<Review> getByWorker(@PathParam("workerId") Long workerId){
        return reviewService.getByWorker(workerId);
    }

    @POST
    @Path("/user/{userId}/job/{jobId}")
    public Review create(
            @PathParam("userId") Long userId,
            @PathParam("jobId") Long jobId,
            Review review){

        return reviewService.create(userId, jobId, review);
    }
}