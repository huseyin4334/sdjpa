package guru.springframework.sdjpa.callbacks;

import guru.springframework.sdjpa.config.SpringContextHelper;
import guru.springframework.sdjpa.model.CreditCard;
import guru.springframework.sdjpa.services.EncryptionService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CreditCardJPACallback {

    private EncryptionService encryptionService;

    @PostConstruct
    private void postConstruct() {
        encryptionService = SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }

    @PrePersist
    @PreUpdate
    public void beforeInsertOrUpdate(CreditCard creditCard) {
        log.info("I'm in before function");

        creditCard.setCreditCardNumber(encryptionService.encrypt(creditCard.getCreditCardNumber()));
    }

    @PostUpdate
    @PostPersist
    @PostLoad
    public void postLoad(CreditCard creditCard) {
        log.info("I'm in postLoad function");

        creditCard.setCreditCardNumber(encryptionService.decrypt(creditCard.getCreditCardNumber()));
    }


}
