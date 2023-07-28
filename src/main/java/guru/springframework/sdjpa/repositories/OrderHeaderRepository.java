package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
}
