package org.app.service;

import org.app.entity.Category;
import org.app.entity.Service;
import org.app.entity.WorkerProfile;
import org.app.repository.CategoryRepository;
import org.app.repository.ServiceRepository;
import org.app.repository.WorkerProfileRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class ServiceService {

    @Inject
    ServiceRepository serviceRepository;

    @Inject
    WorkerProfileRepository workerRepository;

    @Inject
    CategoryRepository categoryRepository;

    public List<Service> getAll(){
        return serviceRepository.listAll();
    }

    public List<Service> getByWorker(Long workerId){
        return serviceRepository.findByWorker(workerId);
    }

    public List<Service> getByCategory(Long categoryId){
        return serviceRepository.findByCategory(categoryId);
    }

    @Transactional
    public Service create(Long workerId, Long categoryId, Service service){

        WorkerProfile worker = workerRepository.findById(workerId);
        if(worker == null){
            throw new RuntimeException("Worker not found");
        }

        Category category = categoryRepository.findById(categoryId);
        if(category == null){
            throw new RuntimeException("Category not found");
        }

        service.worker = worker;
        service.category = category;

        serviceRepository.persist(service);

        return service;
    }
}