package com.recipebook.domain.repository;

import com.recipebook.domain.model.Ingredient;
import com.recipebook.domain.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends BaseRepository<Ingredient> {}
