package org.app.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.app.entity.Category;
import org.app.repository.CategoryRepository;

import java.util.List;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.listAll();
    }

    public Category findbyName(String name){
        return categoryRepository.findByName(name);
    }

    @Transactional
    public Category create(Category category){
        Category existingCategory = categoryRepository.findByName(category.name);

        if(existingCategory != null){
            throw new RuntimeException("Category already exists");
        }

        categoryRepository.persist(category);

        return category;
    }

}
