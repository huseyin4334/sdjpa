package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.OrderHeader;
import guru.springframework.sdjpa.model.OrderLine;
import guru.springframework.sdjpa.model.Product;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.ActiveProfiles;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("default")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OrderHeaderRepositoryTest {

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Inject
    ProductRepository productRepository;

    @Test
    void saveTest() {
        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setCustomer("Huseyin");

        orderHeaderRepository.save(orderHeader);

        assertNotNull(orderHeader.getId());
        assertNotNull(orderHeader.getCreatedDate());
        assertNotNull(orderHeader.getLastModifiedDate());
    }

    @Test
    void testWithOrderLine() {
        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setCustomer("Huseyin-OrderLine");

        orderHeader.setOrderLines(
                Set.of(
                        OrderLine.builder()
                                .orderedQuantity(5)
                                .orderHeader(orderHeader) // we need to reverse append for save
                                .build()
                )
        );

        orderHeaderRepository.save(orderHeader);

        assertNotNull(orderHeader.getId());
        assertNotNull(orderHeader.getOrderLines());
        assertEquals(1, orderHeader.getOrderLines().size());
    }


    @Test
    void testWithOrderLineAndProduct() {
        OrderHeader orderHeader = new OrderHeader();

        orderHeader.setCustomer("Huseyin-OrderLine");

        productRepository.saveAndFlush(
                Product.builder()
                        .name("SavedProduct")
                        .build()
        );

        orderHeader.setOrderLines(
                Set.of(
                        OrderLine.builder()
                                .orderedQuantity(5)
                                .orderHeader(orderHeader) // we need to reverse append for save
                                .product(
                                        productRepository.findOne(
                                                Example.of(
                                                        Product.builder().name("SavedProduct").build(),
                                                        ExampleMatcher.matching()
                                                )
                                        ).orElse(null)
                                )
                                .build()
                )
        );

        orderHeaderRepository.save(orderHeader);

        assertNotNull(orderHeader.getId());
        assertNotNull(orderHeader.getOrderLines());
        assertEquals(1, orderHeader.getOrderLines().size());
        assertNotNull(orderHeader.getOrderLines().iterator().next().getProduct());
    }

}