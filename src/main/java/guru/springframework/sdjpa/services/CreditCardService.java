package guru.springframework.sdjpa.services;

import guru.springframework.sdjpa.model.CreditCard;
import guru.springframework.sdjpa.repositories.creditcard.CreditCardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreditCardService {

    private final CreditCardRepository creditCardRepository;

    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public CreditCard getCreditCardById(Long id) {


        return null;
    }


    @Transactional
    public CreditCard saveCreditCard(CreditCard creditCard) {
        return creditCardRepository.save(creditCard);
    }
}
