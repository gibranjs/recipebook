package com.recipebook.controller;

import com.google.common.reflect.TypeToken;
import com.recipebook.controller.base.BaseController;
import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.Ingredient;
import com.recipebook.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"ingredient","ingredient"})
public class IngredientController extends BaseController<Ingredient> {
    private static TypeToken<Ingredient> _typeToken = new TypeToken<Ingredient>(){};

    @Autowired
    private IngredientService service;

    public IngredientController() {
        super(_typeToken);
    }

    @Override
    public EntityService<Ingredient> getService() { return service; }
}
