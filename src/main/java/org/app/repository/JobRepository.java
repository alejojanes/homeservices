package org.app.repository;

import org.app.entity.Job;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class JobRepository implements PanacheRepository<Job> {

    public List<Job> findByUser(Long userId){
        return find("user.id", userId).list();
    }

    public List<Job> findByWorker(Long workerId){
        return find("worker.id", workerId).list();
    }
}