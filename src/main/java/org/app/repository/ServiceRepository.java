package org.app.repository;

import org.app.entity.Service;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ServiceRepository implements PanacheRepository<Service> {

    public List<Service> findByWorker(Long workerId){
        return find("worker.id", workerId).list();
    }

    public List<Service> findByCategory(Long categoryId){
        return find("category.id", categoryId).list();
    }

    public Service findById(Long id){return find("id", id).firstResult();}
}