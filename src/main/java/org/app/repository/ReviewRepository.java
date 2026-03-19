package org.app.repository;

import org.app.entity.Review;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ReviewRepository implements PanacheRepository<Review> {

    public List<Review> findByWorker(Long workerId){
        return find("worker.id", workerId).list();
    }

    public Double getAverageRating(Long workerId){
        return getEntityManager()
                .createQuery("SELECT AVG(r.rating) FROM Review r WHERE r.worker.id = :workerId", Double.class)
                .setParameter("workerId", workerId)
                .getSingleResult();
    }
}