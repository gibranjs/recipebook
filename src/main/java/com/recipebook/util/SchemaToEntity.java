package com.recipebook.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.reflect.TypeToken;

@SuppressWarnings("unchecked")
public class SchemaToEntity<T> {

    private TypeToken<?> _typeToken;

    private ObjectMapper _mapper;

    public SchemaToEntity(TypeToken<?> typeToken) {
        _mapper = new ObjectMapper();
        _typeToken = typeToken;
    }

    public T dtoToEntity(ObjectNode node, Class<?> clazz) throws JsonProcessingException {
        return (T)_mapper.treeToValue(node, TypeFactory.rawClass(_typeToken.getType()));
    }
}
