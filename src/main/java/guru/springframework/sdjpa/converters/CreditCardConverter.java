package guru.springframework.sdjpa.converters;

import guru.springframework.sdjpa.config.SpringContextHelper;
import guru.springframework.sdjpa.model.CreditCard;
import guru.springframework.sdjpa.services.EncryptionService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Converter
public class CreditCardConverter implements AttributeConverter<String, String> {

    private EncryptionService encryptionService;

    @PostConstruct
    private void postConstruct() {
        encryptionService = SpringContextHelper.getApplicationContext().getBean(EncryptionService.class);
    }

    @Override
    public String convertToDatabaseColumn(String s) {
        return encryptionService.encrypt(s);
    }

    @Override
    public String convertToEntityAttribute(String s) {
        return encryptionService.decrypt(s);
    }
}
