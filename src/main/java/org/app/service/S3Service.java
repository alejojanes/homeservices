package org.app.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jboss.resteasy.reactive.multipart.FileUpload;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.util.UUID;

@ApplicationScoped
public class S3Service {

    @Inject
    S3Client s3Client;

    @ConfigProperty(name = "bucket.amazon.s3")
    private String bucketName;

    public String uploadFile(FileUpload file, Long id) {
        try {
            String fileName = file.fileName();
            String key = "services/"+id+"/"+fileName;

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.contentType())
                    .build();

            s3Client.putObject(
                    request,
                    RequestBody.fromFile(file.uploadedFile())
            );

            // URL pública (si el bucket lo permite)
            return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Error subiendo archivo a S3", e);
        }
    }
}
