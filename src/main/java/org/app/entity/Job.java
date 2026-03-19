package org.app.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job")
public class Job extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Cliente
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    // Trabajador
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    public WorkerProfile worker;

    // Servicio solicitado
    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    public Service service;

    @Enumerated(EnumType.STRING)
    public JobStatus status;

    public String address;

    @Column(name = "phone_contact")
    public String phoneContact;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;
}