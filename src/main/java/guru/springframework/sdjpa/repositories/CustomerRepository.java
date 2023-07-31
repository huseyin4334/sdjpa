package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
