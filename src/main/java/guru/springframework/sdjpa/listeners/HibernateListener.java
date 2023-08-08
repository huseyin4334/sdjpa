package guru.springframework.sdjpa.listeners;

import guru.springframework.sdjpa.annotations.EncryptedString;
import guru.springframework.sdjpa.services.EncryptionService;
import lombok.extern.log4j.Log4j2;
import org.hibernate.event.spi.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Component
public class HibernateListener extends AbstractEncryptionListener implements PreInsertEventListener, PreUpdateEventListener, PostLoadEventListener {

    @Override
    public boolean onPreInsert(PreInsertEvent preInsertEvent) {
        log.info("I'm in onPreInsert");

        this.encrypt(preInsertEvent.getState(), preInsertEvent.getPersister().getPropertyNames(), preInsertEvent.getEntity());

        return false;
    }

    @Override
    public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
        log.info("I'm in onPreUpdate");

        this.encrypt(preUpdateEvent.getState(), preUpdateEvent.getPersister().getPropertyNames(), preUpdateEvent.getEntity());

        return false;
    }

    @Override
    public void onPostLoad(PostLoadEvent postLoadEvent) {
        log.info("I'm in onPostLoad");

        this.decrypt(postLoadEvent.getEntity());
    }
}
