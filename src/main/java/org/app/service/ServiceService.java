package org.app.service;

import org.app.dto.ServiceRequest;
import org.app.dto.ServiceUpdate;
import org.app.entity.*;
import org.app.repository.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.app.util.StatusEnum;

import java.util.List;

@ApplicationScoped
public class ServiceService {

    @Inject
    ServiceRepository serviceRepository;

    @Inject
    WorkerProfileRepository workerRepository;

    @Inject
    CategoryRepository categoryRepository;

    @Inject
    UserRepository  userRepository;

    @Inject
    StatusRepository statusRepository;

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
    public Service createByWorkerAndCategory(Long workerId, Long categoryId, Service service){

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

    @Transactional
    public Service create(ServiceRequest serviceRequest){
        Service service = new Service();

        Category category = categoryRepository.findById(serviceRequest.category);
        if(category == null){
            throw new RuntimeException("Category not found");
        }

        User user = userRepository.findByEmail(serviceRequest.email);
        if(user == null){
            throw new RuntimeException("Usuario not found");
        }

        service.user = user;
        service.category = category;
        service.price = serviceRequest.price;
        service.description = serviceRequest.description;
        service.title = serviceRequest.title;
        service.status = statusRepository.findbyName(StatusEnum.Created.name());

        serviceRepository.persist(service);

        return service;

    }

    @Transactional
    public Service update(ServiceUpdate serviceUpdate){
        Service existingService = serviceRepository.findById(serviceUpdate.id);

        if(existingService == null){
            throw new RuntimeException("Service not found");
        }

        if(serviceUpdate.status.equals(StatusEnum.Cancelled.getClave())){
            existingService.status = statusRepository.findbyName(StatusEnum.Cancelled.name());
            serviceRepository.persist(existingService);

            return existingService;
        }

        WorkerProfile wpExists = workerRepository.findById(serviceUpdate.workerid);
        if(wpExists == null){
            throw new RuntimeException("Worker not found");
        }

        existingService.status = statusRepository.findbyName(StatusEnum.Assigned.name());

        existingService.worker = wpExists;
        existingService.price = serviceUpdate.price;
        existingService.description = serviceUpdate.description;

        serviceRepository.persist(existingService);

        return existingService;
    }

}