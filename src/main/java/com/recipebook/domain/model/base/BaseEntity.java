package com.recipebook.domain.model.base;

import com.recipebook.util.ApiUtil;
import com.recipebook.util.EntityInspector;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Map;

@MappedSuperclass
@Getter @NoArgsConstructor @ToString
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    protected static final Logger logger = LoggerFactory.getLogger(BaseEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT")
    private Long id;

    @Override
    public boolean equals(Object entity) {
        if ( !(entity instanceof BaseEntity) )
            return false;

        Map<String, Field> fields = EntityInspector.getFieldsMap(this.getClass());
        for (Field field : fields.values()) {
            try {
                if ( this.getFieldValue(field) != ((BaseEntity)entity).getFieldValue(field) )
                    return false;
            } catch (Exception ex) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Object clone() {
        return this.clone(false);
    }

    public Object clone(Boolean fullCopy) {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            BaseEntity newObj;

            try {
                newObj = this.getClass().newInstance();
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("Could not clone current object", e);
                return null;
            }

            Map<String, Field> fields = EntityInspector.getFieldsMap(this.getClass());

            for (Field field : fields.values()) {
                if ( fullCopy ) {
                    // this will allow to copy fields that are readonly
                    field.setAccessible(true);
                }

                Object value = null;
                try {
                    value = this.getFieldValue(field);
                    if ( value != null )
                        newObj.setFieldValue(field, value);
                } catch (Exception exc) {
                    logger.warn("Could not set value {} for field {}", String.valueOf(value), field.getName());
                }
            }

            return newObj;
        }
    }

    public Object getFieldValue(String fieldName) throws Exception {

        Field field = this.getField(fieldName);

        if ( field == null )
            throw new NoSuchFieldException(String.format("Field %s not found", fieldName));

        return this.getFieldValue(field);
    }

    public Object getFieldValue(Field field) throws Exception {

        if ( field.isAccessible() )
            return field.get(this);

        return this.runGetter(field);
    }

    public void setFieldValue(String fieldName, Object value) throws Exception {

        Field field = this.getField(fieldName);

        if ( field == null )
            throw new NoSuchFieldException(String.format("Field %s not found", fieldName));

        this.setFieldValue(field, value);
    }

    public void setFieldValue(Field field, Object value) throws Exception {

        if ( field.isAccessible() )
            field.set(this, value);
        else
            this.runSetter(field, value);
    }

    private Object runGetter(Field field) throws Exception {
        String methodName = "get" + StringUtils.capitalize(field.getName());
        return ApiUtil.executeMethod(this, methodName);
    }

    private void runSetter(Field field, Object value) throws Exception {
        String methodName = "set" + StringUtils.capitalize(field.getName());
        ApiUtil.executeMethod(this, methodName, value);
    }

    private Field getField(String fieldName) {
        Map<String, Field> fields = EntityInspector.getFieldsMap(this.getClass());
        return fields.get(fieldName);
    }
}
