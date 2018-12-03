package com.recipebook.util;

import com.google.common.collect.ObjectArrays;

import javax.annotation.Nonnull;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unused")
public class EntityInspector {
    public static Field[] getFieldsUpTo(@Nonnull Class<?> type) {

        Field[] result = type.getDeclaredFields();

        Class<?> parentClass = type.getSuperclass();
        if ( parentClass != null ) {
            Field[] parentClassFields = getFieldsUpTo(parentClass);
            result = ObjectArrays.concat(result, parentClassFields, Field.class);
        }

        return result;
    }

    public static Map<String, Field> getFieldsMap(@Nonnull Class<?> type) {

        Field[] fields = getFieldsUpTo(type);

        Map<String, Field> result = new HashMap<>();

        for (Field objField : fields) {
            result.put(objField.getName(), objField);
        }

        return result;
    }

    public static Field getField(@Nonnull Class<?> type, String fieldName) {

        try {
            return type.getDeclaredField(fieldName);
        } catch (NoSuchFieldException ex) {
            Class<?> parentClass = type.getSuperclass();
            if ( parentClass != null )
                return getField(parentClass, fieldName);
        }

        return null;
    }
}
