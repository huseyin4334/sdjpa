package guru.springframework.sdjpa.interceptors;

import guru.springframework.sdjpa.annotations.EncryptedString;
import guru.springframework.sdjpa.services.EncryptionService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class EncryptionInterceptor implements Interceptor {

    @Autowired
    private EncryptionService encryptionService;

    @Override
    public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("I'm in onSave");
        Object[] newState = processFields(entity, state, propertyNames, "onSave");
        return Interceptor.super.onSave(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onLoad(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("I'm in onLoad");
        Object[] newState = processFields(entity, state, propertyNames, "onLoad");
        return Interceptor.super.onLoad(entity, id, newState, propertyNames, types);
    }

    @Override
    public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
        log.info("I'm in onFlushDirty");
        Object[] newState = processFields(entity, currentState, propertyNames, "onFlushDirty");
        return Interceptor.super.onFlushDirty(entity, id, newState, previousState, propertyNames, types);
    }

    private Object[] processFields(Object entity,
                                   Object[] state, // States are entity row values
                                   String[] propertyNames, // column names
                                   String type) {
        List<String> fields = getAnnotationFields(entity);

        for (String field : fields) {
            for (int i = 0; i < propertyNames.length; i++) {
                if(propertyNames[i].equals(field) && StringUtils.hasText(state[i].toString())) {
                    if ("onSave".equals(type) || "onFlushDirty".equals(type)) {
                        state[i] = encryptionService.encrypt(state[i].toString());
                    } else if ("onLoad".equals(type)) {
                        state[i] = encryptionService.decrypt(state[i].toString());
                    }
                }
            }
        }
        return state;
    }

    private List<String> getAnnotationFields(Object entity) {
        List<String> fields = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.getAnnotation(EncryptedString.class) != null)
                fields.add(field.getName());
        }
        return fields;
    }
}
