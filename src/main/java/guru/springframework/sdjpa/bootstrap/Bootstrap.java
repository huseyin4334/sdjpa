package guru.springframework.sdjpa.bootstrap;

import guru.springframework.sdjpa.model.Customer;
import guru.springframework.sdjpa.model.Product;
import guru.springframework.sdjpa.repositories.CustomerRepository;
import guru.springframework.sdjpa.repositories.ProductRepository;
import guru.springframework.sdjpa.services.BootstrapService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class Bootstrap implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BootstrapService bootstrapService;


    public void optimisticLockDemo() {
        Customer customer = Customer.builder().customerName("Testing Version").build();

        Customer saved = customerRepository.save(customer);
        log.info("Version is: {}", saved.getVersion());

        // this scenario will give error. Because we didn't take updated customer.
        // We will take error when we save entity with old version,
        /*

        saved.setCustomerName("Testing Version 2");
        customerRepository.save(saved);
        log.info("Version is: {}", saved.getVersion());

        saved.setCustomerName("Testing Version 3");
        customerRepository.save(saved);
        log.info("Version is: {}", saved.getVersion());

        saved.setCustomerName("Testing Version 4");
        customerRepository.save(saved);
        log.info("Version is: {}", saved.getVersion());

         */

        // you will see in logs that is executed select function before update function.
        // Because hibernate taking version with select in optimistic lock.
        // Later It's updating with this version. (where customer_id = ? and version = ?)
        saved.setCustomerName("Testing Version 2");
        Customer saved2 = customerRepository.save(saved);
        log.info("Version is: {}", saved2.getVersion());

        saved2.setCustomerName("Testing Version 3");
        Customer saved3 = customerRepository.save(saved2);
        log.info("Version is: {}", saved3.getVersion());

        customerRepository.deleteById(saved3.getId());
    }

    private void transactionManagement() {
        Product p = Product.builder()
                .name("Mty T product")
                .build();

        Product saved = productRepository.save(p);

        Product saved2 = bootstrapService.updateAndReturnSaved(p.getId(), 25);
    }

    @Override
    public void run(String... args) throws Exception {

        optimisticLockDemo();

        transactionManagement();
    }
}
