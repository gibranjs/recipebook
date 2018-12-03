package com.recipebook.domain.repository;

import com.recipebook.domain.model.Recipe;
import com.recipebook.domain.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends BaseRepository<Recipe> {
    Iterable<Recipe> findByUserId(Long id);
}
