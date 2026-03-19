package org.app.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "review")
public class Review extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    // Cliente que califica
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    // Trabajador calificado
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    public WorkerProfile worker;

    // Job asociado
    @OneToOne
    @JoinColumn(name = "job_id", nullable = false, unique = true)
    public Job job;

    @Column(nullable = false)
    public Integer rating; // 1 a 5

    public String comment;

    @Column(name = "created_at")
    public LocalDateTime createdAt;
}