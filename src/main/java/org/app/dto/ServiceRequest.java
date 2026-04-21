package org.app.dto;


import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

import java.math.BigDecimal;
import java.util.List;

public class ServiceRequest {

    @RestForm
    public String email;

    @RestForm
    public Long category;

    @RestForm
    public String title;

    @RestForm
    public String description;

    @RestForm
    public BigDecimal price;

    // Lista de imágenes
    @RestForm("images")
    public List<FileUpload> images;
}
