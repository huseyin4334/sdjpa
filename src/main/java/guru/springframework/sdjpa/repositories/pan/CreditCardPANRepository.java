package guru.springframework.sdjpa.repositories.pan;

import guru.springframework.sdjpa.model.CreditCardPAN;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardPANRepository extends JpaRepository<CreditCardPAN, Long> {
}
