package guru.springframework.sdjpa.config;

import guru.springframework.sdjpa.listeners.HibernateListener;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostLoadEventListener;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ListenerRegistration implements BeanPostProcessor {

    @Autowired
    private HibernateListener hibernateListener;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if (bean instanceof LocalContainerEntityManagerFactoryBean){
            EventListenerRegistry registry = ((SessionFactoryImpl)((LocalContainerEntityManagerFactoryBean) bean).getNativeEntityManagerFactory())
                    .getServiceRegistry()
                    .getService(EventListenerRegistry.class);

            registry.appendListeners(EventType.POST_LOAD, hibernateListener);
            registry.appendListeners(EventType.PRE_INSERT, hibernateListener);
            registry.appendListeners(EventType.PRE_UPDATE, hibernateListener);
        }

        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
