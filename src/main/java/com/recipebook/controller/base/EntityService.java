package com.recipebook.controller.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.recipebook.domain.model.base.BaseEntity;
import com.recipebook.exception.EntityNotFoundException;
import com.recipebook.exception.InvalidReferenceException;
import org.slf4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface EntityService<T extends BaseEntity> {

    // get logger from implemented classes for default methods.
    Logger getLogger();

    default Class<?> getEntityClass() { return null; }

    // GET (List)
    default Iterable<T> getAll() {
        return getAll(null);
    }
    Iterable<T> getAll(Specification<T> spec);
    Page<T> getAll(Specification<T> spec, Pageable pageable);


    // Get support
    default T get(String objKey) throws EntityNotFoundException {
        // see object id is given
        Long id = Long.valueOf(objKey);
        return get(id);
    }

    default T get(Long id) throws EntityNotFoundException {
        T objInst = _get(id);
        if ( objInst == null )
            throw new EntityNotFoundException(getEntityClass(), "id", id.toString());

        return objInst;
    }
    T _get(Long id);


    // POST support
    T save(T objInstance);


    // PUT Support
    default T update(String objKey, ObjectNode updateData) throws EntityNotFoundException {
        T oldInstance = get(objKey);
        try {
            T updatedObjInst = new ObjectMapper().readerForUpdating(oldInstance.clone(true)).readValue(updateData);
            getLogger().debug(" EntityService.update:: updatedObjInst: {}", updatedObjInst);
            return update(updatedObjInst);
        } catch (InvalidFormatException ex) {
            getLogger().warn(" EntityService.update: Exception: {} ", ex.getMessage());
            throw new InvalidReferenceException(ex.getPathReference(), ex.getPath().get(0).getFieldName(),
                    ex.getValue(), "Invalid field value");
        } catch(Exception ex) {
            getLogger().warn(" EntityService.update: Exception: {} ", ex.getMessage());
        }
        return null;
    }
    T update(T objInstance);


    // DELETE support
    default void delete(String objKey) throws EntityNotFoundException {
        // see object id is given
        T objInstance = get(objKey);

        if ( objInstance != null ) {
            delete(objInstance);
        }
    }

    void delete(T objInstance);
}
