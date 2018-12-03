package com.recipebook.service;

import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.RecipeStep;
import com.recipebook.domain.repository.RecipeStepRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeStepService implements EntityService<RecipeStep> {
    protected static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeStepRepository repo;

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public Iterable<RecipeStep> getAll(Specification<RecipeStep> spec) {
        return repo.findAll();
    }

    @Override
    public Page<RecipeStep> getAll(Specification<RecipeStep> spec, Pageable pageable) {
        return repo.findAll(spec, pageable);
    }

    @Override
    public RecipeStep _get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public RecipeStep save(RecipeStep objInstance) {
        return repo.save(objInstance);
    }

    @Override
    public RecipeStep update(RecipeStep objInstance) {
        Optional<RecipeStep> recipe = repo.findById(objInstance.getId());
        if ( !recipe.isPresent() )
            return null; // throw exception

        return repo.save(objInstance);
    }

    @Override
    public void delete(RecipeStep objInstance) {
        repo.delete(objInstance);
    }
}
