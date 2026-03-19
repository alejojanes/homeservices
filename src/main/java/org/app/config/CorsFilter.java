package org.app.config;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MultivaluedMap;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        // 🔥 Manejar preflight (OPTIONS)
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {

            Response.ResponseBuilder response = Response.ok();

            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            response.header("Access-Control-Allow-Headers", "accept,authorization,content-type,x-requested-with");
            response.header("Access-Control-Allow-Credentials", "true");

            requestContext.abortWith(response.build());
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        MultivaluedMap<String, Object> headers = responseContext.getHeaders();

        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
        headers.add("Access-Control-Allow-Headers", "accept,authorization,content-type,x-requested-with");
        headers.add("Access-Control-Allow-Credentials", "true");
    }
}