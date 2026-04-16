package org.app.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.entity.WorkerProfile;

import java.util.List;

@ApplicationScoped
public class WorkerProfileRepository implements PanacheRepository<WorkerProfile> {

    public WorkerProfile findByUserId(Long userId){
        return find("user.id", userId).firstResult();
    }

    public List<WorkerProfile> getAllByCategory(Long id){ return find("category.id", id).list(); }
}