package org.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "worker_profile")
public class WorkerProfile extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonIgnoreProperties("workerProfile")
    public User user;

    public String description;

    @Column(name = "experience_years")
    public Integer experienceYears;

    public Boolean verified = false;

    public Double rating = 0.0;
}