package guru.springframework.sdjpa.repositories;

import guru.springframework.sdjpa.model.Product;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    void saveProduct() {
        Product p = Product.builder()
                .name("Test prod")
                .build();

        Product saved = productRepository.saveAndFlush(p);

        saved.setQuantityOnHand(25);

        productRepository.saveAndFlush(saved);

        // you will see "for update" in hibernate query
        // this keyword is locked rows for updating until finish to read.
        saved = productRepository.findById(saved.getId()).orElse(null);

        System.out.println(saved);
    }

}