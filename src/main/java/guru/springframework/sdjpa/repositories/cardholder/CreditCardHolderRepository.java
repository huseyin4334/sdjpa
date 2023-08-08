package guru.springframework.sdjpa.repositories.cardholder;

import guru.springframework.sdjpa.model.CreditCardHolder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardHolderRepository extends JpaRepository<CreditCardHolder, Long> {
}
