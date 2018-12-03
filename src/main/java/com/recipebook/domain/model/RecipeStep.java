package com.recipebook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipebook.domain.model.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class RecipeStep extends BaseEntity {
    @Column(name = "instructions")
    private String instructions;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private Recipe recipe;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "recipeStepsIngredients",
        joinColumns = @JoinColumn(name = "recipeStepId"),
        inverseJoinColumns = @JoinColumn(name = "ingredientId")
    )
    private Set<Ingredient> ingredients;
}
