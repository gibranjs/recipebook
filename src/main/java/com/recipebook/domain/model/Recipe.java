package com.recipebook.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipebook.domain.model.base.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter @Setter
public class Recipe extends BaseObject {
    @Column(name = "description")
    @Size(max = 255, message = "{validation.description.size}")
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false, referencedColumnName = "id")
    @JsonIgnore
    private UserContact user;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.EAGER, orphanRemoval = true)
    private List<RecipeStep> recipeSteps;
}
