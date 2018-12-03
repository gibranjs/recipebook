package com.recipebook.controller.base;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.reflect.TypeToken;
import com.recipebook.domain.model.base.BaseEntity;

import com.recipebook.exception.EntityNotFoundException;
import com.recipebook.util.EntityServiceRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings("unused,unchecked")
public class BaseController<T extends BaseEntity> {
    protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected TypeToken<T> _typeToken;

    protected BaseController(TypeToken<T> typeToken) {
        _typeToken = typeToken;
    }

    protected EntityService<T> getService() {
        return (EntityService<T>) EntityServiceRegistry.getService(_typeToken.getRawType());
    }

    protected String getEntityType() {
        return _typeToken.getRawType().getSimpleName();
    }

    @GetMapping(value = {"", "/"})
    private Iterable<T> list(@RequestParam MultiValueMap<String, String> params) {
        return getService().getAll();
    }

    @GetMapping(value = "/{objKey}")
    private T get(@PathVariable("objKey") String objKey) throws EntityNotFoundException {
        return getService().get(objKey);
    }

    @PutMapping(value = "/{objKey}")
    private T update(@PathVariable("objKey") String objKey, @RequestBody ObjectNode objInst) throws EntityNotFoundException {
        return getService().update(objKey, objInst);
    }

    @PostMapping(value = {"","/"})
    @ResponseStatus(HttpStatus.CREATED)
    private T save(@RequestBody T objInst) {
        return getService().save(objInst);
    }

    @DeleteMapping(value = "/{objKey}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable("objKey") String objKey) throws EntityNotFoundException {
        getService().delete(objKey);
    }
}
