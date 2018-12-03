package com.recipebook.service;

import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.Recipe;
import com.recipebook.domain.repository.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService implements EntityService<Recipe> {
    protected static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeRepository repo;

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public Iterable<Recipe> getAll(Specification<Recipe> spec) {
        return repo.findAll();
    }

    @Override
    public Page<Recipe> getAll(Specification<Recipe> spec, Pageable pageable) {
        return repo.findAll(spec, pageable);
    }

    @Override
    public Recipe _get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Recipe save(Recipe objInstance) {
        return repo.save(objInstance);
    }

    @Override
    public Recipe update(Recipe objInstance) {
        Optional<Recipe> recipe = repo.findById(objInstance.getId());
        if ( !recipe.isPresent() )
            return null; // throw exception

        return repo.save(objInstance);
    }

    @Override
    public void delete(Recipe objInstance) {
        repo.delete(objInstance);
    }

    public Iterable<Recipe> getUserRecipes(Long id) {
        return repo.findByUserId(id);
    }
}
