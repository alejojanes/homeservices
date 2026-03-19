package org.app.service;

import org.app.entity.*;
import org.app.repository.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class ReviewService {

    @Inject ReviewRepository reviewRepository;
    @Inject JobRepository jobRepository;
    @Inject WorkerProfileRepository workerRepository;
    @Inject UserRepository userRepository;

    public List<Review> getByWorker(Long workerId){
        return reviewRepository.findByWorker(workerId);
    }

    @Transactional
    public Review create(Long userId, Long jobId, Review review){

        Job job = jobRepository.findById(jobId);
        if(job == null) throw new RuntimeException("Job not found");

        if(!job.user.id.equals(userId)){
            throw new RuntimeException("User not authorized to review this job");
        }

        if(!job.status.equals(JobStatus.COMPLETED)){
            throw new RuntimeException("Job must be completed before review");
        }

        User user = userRepository.findById(userId);
        if(user == null) throw new RuntimeException("User not found");

        // evitar duplicados
        Review existing = reviewRepository.find("job.id", jobId).firstResult();
        if(existing != null){
            throw new RuntimeException("Review already exists for this job");
        }

        review.user = user;
        review.worker = job.worker;
        review.job = job;
        review.createdAt = LocalDateTime.now();

        reviewRepository.persist(review);

        // 🔥 recalcular rating del worker
        updateWorkerRating(job.worker.id);

        return review;
    }

    // ⭐ lógica clave
    private void updateWorkerRating(Long workerId){

        Double avg = reviewRepository.getAverageRating(workerId);

        WorkerProfile worker = workerRepository.findById(workerId);

        if(avg == null){
            worker.rating = 0.0;
        } else {
            worker.rating = avg;
        }
    }
}