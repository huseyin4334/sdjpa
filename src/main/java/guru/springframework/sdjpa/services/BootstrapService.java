package guru.springframework.sdjpa.services;

import guru.springframework.sdjpa.model.Product;
import guru.springframework.sdjpa.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BootstrapService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product updateAndReturnSaved(Long id, Integer qoh) {
        Product p = productRepository.findById(id).orElseThrow();

        p.setQuantityOnHand(qoh);

        return productRepository.saveAndFlush(p);


        /*
            17.line
                repository function will change transaction.
                Because function is using lock when reading data.
            21.line
                Project will be thrown. Because transaction is read only anymore because of 17.line.
                We have to use Transactional annotation for fix this problem.
                EntityManagerFactory will create a transaction for this line.

         */

    }
}
