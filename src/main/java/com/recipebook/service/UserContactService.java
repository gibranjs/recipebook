package com.recipebook.service;

import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.Recipe;
import com.recipebook.domain.model.UserContact;
import com.recipebook.domain.repository.UserContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserContactService implements EntityService<UserContact> {
    protected static final Logger logger = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private UserContactRepository repo;

    @Autowired
    private RecipeService recipeService;

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public Iterable<UserContact> getAll(Specification<UserContact> spec) {
        return repo.findAll();
    }

    @Override
    public Page<UserContact> getAll(Specification<UserContact> spec, Pageable pageable) {
        return repo.findAll(spec, pageable);
    }

    @Override
    public UserContact _get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public UserContact save(UserContact objInstance) {
        return repo.save(objInstance);
    }

    @Override
    public UserContact update(UserContact objInstance) {
        Optional<UserContact> recipe = repo.findById(objInstance.getId());
        if ( !recipe.isPresent() )
            return null; // throw exception

        return repo.save(objInstance);
    }

    @Override
    public void delete(UserContact objInstance) {
        repo.delete(objInstance);
    }

    public Iterable<Recipe> getUserRecipes(Long id) {
        return recipeService.getUserRecipes(id);
    }
}
