package com.recipebook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.recipebook.domain.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter @Setter
public class Ingredient extends BaseObject {
    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
        name = "recipeStepsIngredients",
        joinColumns = @JoinColumn(name = "ingredientId"),
        inverseJoinColumns = @JoinColumn(name = "recipeStepId")
    )
    @JsonIgnoreProperties("ingredients")
    private Set<RecipeStep> recipeSteps;
}
