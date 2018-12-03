package com.recipebook.controller;

import com.google.common.reflect.TypeToken;
import com.recipebook.controller.base.BaseController;
import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.RecipeStep;
import com.recipebook.service.RecipeStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"recipeStep","recipeStep"})
public class RecipeStepController extends BaseController<RecipeStep> {
    private static TypeToken<RecipeStep> _typeToken = new TypeToken<RecipeStep>(){};

    @Autowired
    private RecipeStepService service;

    public RecipeStepController() {
        super(_typeToken);
    }

    @Override
    public EntityService<RecipeStep> getService() { return service; }
}
