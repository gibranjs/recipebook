package com.recipebook.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ApiUtil {
    private static final Logger logger = LoggerFactory.getLogger(ApiUtil.class);

    public static String DATE_FIELD_FORMAT = "yyyy-M-dd hh:mm:ss";

    public static Method getMethod(Class<?> targetClass, String methodName) throws NoSuchMethodException {
        return getMethod(targetClass, methodName, false);
    }

    public static Method getMethod(Class<?> targetClass, String methodName, Boolean caseSensitive, Class<?>... paramsTypes)
            throws NoSuchMethodException {

        if ( caseSensitive ) {
            try {
                return targetClass.getMethod(methodName, paramsTypes);
            } catch(NoSuchMethodException ex) {
                logger.warn(String.format("Method not found \"%s\"", methodName));
                throw ex;
            }
        }

        for ( Method method : targetClass.getMethods() ) {
            if ( StringUtils.equalsIgnoreCase(method.getName(), methodName) )
                return method;
        }

        throw new NoSuchMethodException(String.format("Method not found \"%s\"", methodName));
    }

    public static Object executeMethod(Object target, String methodName, Object... args)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IllegalArgumentException, NullPointerException {

        Method method = getMethod(target.getClass(), methodName);

        try {
            return method.invoke(target, args);
        } catch(IllegalArgumentException ex) {
            logger.warn(String.format("Illegal arguments for method \"%s\"", method.getName()), ex);
            throw ex;
        } catch(IllegalAccessException ex) {
            logger.warn(String.format("Could not access method \"%s\"", method.getName()), ex);
            throw ex;
        } catch (InvocationTargetException ex) {
            logger.warn(String.format("Could not invoke method \"%s\"", method.getName()), ex);
            throw ex;
        } catch(NullPointerException ex) {
            logger.warn(String.format("Null pointer exception invoking method \"%s\"", method.getName()), ex);
            throw ex;
        }
    }

    public static Object parseValueByType(String valueStr, Class type) throws Exception {
        return parseValueByType(valueStr, type, null);
    }

    public static Object parseValueByType(String valueStr, Class type, Object defaultValue) throws Exception {
        if ( StringUtils.isBlank(valueStr) )
            return defaultValue;

        String value = valueStr.trim();

        if ( Date.class.isAssignableFrom(type) ) {
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_FIELD_FORMAT, Locale.ENGLISH);
            try {
                return formatter.parse(value);
            } catch(ParseException ex) {
                logger.error("Error parsing date string with format {}: {}", DATE_FIELD_FORMAT, ex.getMessage());
                throw ex;
            }
        } else if ( Integer.class.isAssignableFrom(type) ) {
            return Integer.valueOf(value);
        } else if ( Double.class.isAssignableFrom(type) ) {
            return Double.valueOf(value);
        } else if ( Float.class.isAssignableFrom(type)) {
            return Float.valueOf(value);
        } else if ( Boolean.class.isAssignableFrom(type) ) {
            return Boolean.getBoolean(value);
        } else if ( String.class.isAssignableFrom(type) ) {
            return value;
        }

        return defaultValue;
    }
}
