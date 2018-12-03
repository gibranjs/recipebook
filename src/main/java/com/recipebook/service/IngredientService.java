package com.recipebook.service;

import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.Ingredient;
import com.recipebook.domain.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IngredientService implements EntityService<Ingredient> {
    protected static final Logger logger = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
    private IngredientRepository repo;

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public Iterable<Ingredient> getAll(Specification<Ingredient> spec) {
        return repo.findAll();
    }

    @Override
    public Page<Ingredient> getAll(Specification<Ingredient> spec, Pageable pageable) {
        return repo.findAll(spec, pageable);
    }

    @Override
    public Ingredient _get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Ingredient save(Ingredient objInstance) {
        return repo.save(objInstance);
    }

    @Override
    public Ingredient update(Ingredient objInstance) {
        Optional<Ingredient> ingredient = repo.findById(objInstance.getId());
        if ( !ingredient.isPresent() )
            return null; // throw exception

        return repo.save(objInstance);
    }

    @Override
    public void delete(Ingredient objInstance) {
        repo.delete(objInstance);
    }
}
