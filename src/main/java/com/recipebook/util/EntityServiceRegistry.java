package com.recipebook.util;

import com.recipebook.controller.base.EntityService;

import java.util.HashMap;
import java.util.Map;

public class EntityServiceRegistry {

    // support for registration and dynamic resolution
    private static Map<Class<?>, EntityService> serviceMap = new HashMap<>();

    public static void register(Class<?> clazz, EntityService entityService) {
        serviceMap.put(clazz, entityService);
    }

    public static EntityService getService(Class<?> clazz) {
        return serviceMap.get(clazz);
    }
}
