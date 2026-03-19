package org.app.service;

import org.app.entity.*;
import org.app.repository.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class JobService {

    @Inject JobRepository jobRepository;
    @Inject UserRepository userRepository;
    @Inject WorkerProfileRepository workerRepository;
    @Inject ServiceRepository serviceRepository;

    public List<Job> getByUser(Long userId){
        return jobRepository.findByUser(userId);
    }

    public List<Job> getByWorker(Long workerId){
        return jobRepository.findByWorker(workerId);
    }

    @Transactional
    public Job create(Long userId, Long workerId, Long serviceId, Job job){

        User user = userRepository.findById(userId);
        if(user == null) throw new RuntimeException("User not found");

        WorkerProfile worker = workerRepository.findById(workerId);
        if(worker == null) throw new RuntimeException("Worker not found");

        Service service = serviceRepository.findById(serviceId);
        if(service == null) throw new RuntimeException("Service not found");

        job.user = user;
        job.worker = worker;
        job.service = service;

        job.status = JobStatus.REQUESTED;
        job.createdAt = LocalDateTime.now();
        job.updatedAt = LocalDateTime.now();

        jobRepository.persist(job);

        return job;
    }

    // 🔥 Cambiar estado
    @Transactional
    public Job updateStatus(Long jobId, JobStatus status){

        Job job = jobRepository.findById(jobId);

        if(job == null){
            throw new RuntimeException("Job not found");
        }

        job.status = status;
        job.updatedAt = LocalDateTime.now();

        return job;
    }
}