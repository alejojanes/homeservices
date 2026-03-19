package org.app.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "service")
public class Service extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // 🔥 Relación con Worker
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    @JsonIgnoreProperties("workerProfile")
    public WorkerProfile worker;

    // 🔥 Relación con Category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    public Category category;

    @Column(nullable = false)
    public String title;

    public String description;

    public Double price;
}