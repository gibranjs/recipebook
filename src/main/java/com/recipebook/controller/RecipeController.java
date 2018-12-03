package com.recipebook.controller;

import com.google.common.reflect.TypeToken;
import com.recipebook.controller.base.BaseController;
import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.Recipe;
import com.recipebook.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"recipe","recipe"})
public class RecipeController extends BaseController<Recipe> {
    private static TypeToken<Recipe> _typeToken = new TypeToken<Recipe>(){};

    @Autowired
    private RecipeService service;

    public RecipeController() {
        super(_typeToken);
    }

    @Override
    public EntityService<Recipe> getService() { return service; }
}
