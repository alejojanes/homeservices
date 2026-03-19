package org.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String name;

    @Column(nullable = false)
    public String lastname;

    @Column(unique = true)
    public String email;

    public String phone;

    public String password;

    public String role;

    @OneToOne(mappedBy = "user")
    @JsonIgnore
    public WorkerProfile workerProfile;
}