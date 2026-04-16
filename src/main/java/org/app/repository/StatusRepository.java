package org.app.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.app.entity.Status;

@ApplicationScoped
public class StatusRepository implements PanacheRepository<Status> {

    public Status findbyName(String name){
        return find("name",name).firstResult();
    }

}
