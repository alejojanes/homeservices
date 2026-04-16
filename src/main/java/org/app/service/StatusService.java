package org.app.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.app.entity.Category;
import org.app.entity.Status;
import org.app.repository.StatusRepository;

import java.util.List;

@ApplicationScoped
public class StatusService {

    @Inject
    StatusRepository  statusRepository;

    public List<Status> getAllStatus(){
        return statusRepository.listAll();
    }

    public Status create(Status status){

        Status existsStatus = statusRepository.findbyName(status.name);

        if(existsStatus != null){
            throw new RuntimeException("Status already registered");
        }

        statusRepository.persist(status);

        return status;
    }
}
