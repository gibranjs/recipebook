package com.recipebook.controller;

import com.google.common.reflect.TypeToken;
import com.recipebook.controller.base.BaseController;
import com.recipebook.controller.base.EntityService;
import com.recipebook.domain.model.Recipe;
import com.recipebook.domain.model.UserContact;
import com.recipebook.exception.EntityNotFoundException;
import com.recipebook.service.UserContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = {"userContact","userContact"})
public class UserContactController extends BaseController<UserContact> {
    private static TypeToken<UserContact> _typeToken = new TypeToken<UserContact>(){};

    @Autowired
    private UserContactService service;

    public UserContactController() {
        super(_typeToken);
    }

    @Override
    public EntityService<UserContact> getService() { return service; }

    @GetMapping(value = "/{objKey}/recipes")
    private Iterable<Recipe> getRecipes(@PathVariable("objKey") String objKey) throws EntityNotFoundException {
        Long id = Long.valueOf(objKey);
        if ( service.get(id) == null )
            throw new EntityNotFoundException(UserContact.class, "id", id.toString());

        return service.getUserRecipes(id);
    }
}
