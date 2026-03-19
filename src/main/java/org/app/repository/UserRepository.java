package org.app.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.entity.User;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public User findByEmail(String email){
        return find("email", email).firstResult();
    }
}