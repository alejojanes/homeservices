package org.app.controller;

import io.quarkus.security.Authenticated;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.app.entity.Category;
import org.app.service.CategoryService;

import java.util.List;

@Path("/category")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Authenticated
public class CategoryController {

    @Inject
    CategoryService categoryService;

    @GET
    public List<Category> getAll(){
        return categoryService.getAllCategories();
    }

    @POST
    public Category create(Category category){
        return categoryService.create(category);
    }
}
