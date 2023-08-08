package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.CreditCard;
import guru.springframework.sdjpa.repositories.creditcard.CreditCardRepository;
import guru.springframework.sdjpa.services.EncryptionService;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("default")
class CreditCardRepositoryTest {

    private final String CREDIT_CARD_NUMBER = "12345678900000";


    @Inject
    CreditCardRepository creditCardRepository;

    @Inject
    EncryptionService encryptionService;

    @Inject
    JdbcTemplate jdbcTemplate;

    @Test
    void testSaveAndStoreCreditCard() {
        CreditCard card = new CreditCard();

        card.setCreditCardNumber(CREDIT_CARD_NUMBER);
        card.setCvv("123");
        card.setExpirationDate("12/2028");

        CreditCard saved = creditCardRepository.saveAndFlush(card);

        CreditCard fetchedCard = creditCardRepository.findById(saved.getId()).get();

        Map<String, Object> dbRow = jdbcTemplate.queryForMap("select * from credit_card where id = " + saved.getId());
        String dbCardValue = (String) dbRow.get("credit_card_number");

        assertNotEquals(saved.getCreditCardNumber(), dbCardValue);
        assertEquals(dbCardValue, encryptionService.encrypt(saved.getCreditCardNumber()));
        assertEquals(saved.getCreditCardNumber(), fetchedCard.getCreditCardNumber());
    }

}