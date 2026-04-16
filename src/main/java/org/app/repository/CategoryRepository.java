package org.app.repository;

import org.app.entity.Category;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Category> {

    public Category findByName(String name){
        return find("name LIKE ?1", "%" + name + "%").firstResult();
    }

}