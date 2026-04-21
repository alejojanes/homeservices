package org.app.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import software.amazon.awssdk.services.s3.S3Client;

@ApplicationScoped
public class S3Config {

    @Produces
    @ApplicationScoped
    public S3Client s3Client() {
        return S3Client.builder().build();
    }
}