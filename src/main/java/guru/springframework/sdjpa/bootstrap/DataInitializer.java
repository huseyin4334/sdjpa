package guru.springframework.sdjpa.bootstrap;

import guru.springframework.sdjpa.model.Book;
import guru.springframework.sdjpa.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"}) // run jost for this profiles
@Slf4j
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        /*
            That will run with h2 in-memory database. Because h2 initializes with project started.
            However, scope of h2 is runtime. Therefore, default database is h2 for our project.
            hibernate have been started default by Spring data jpa.

            We can see hibernate sql logs with defining spring.jpa.show-sql=true parameter in application.properties
            (
                However, we can use those parameters together for logging similarly
                1.spring.jpa.properties.hibernate.show_sql=true
                2.spring.jpa.properties.hibernate.format_sql=true
                3.logging.level.org.hibernate.type.descriptor.sql=trace -> that's for show bind values
            )

            H2 database start randomly when every initializing the application. We can see connection url in console. It's creating with UUID.
            localhost:8080/h2-console/login.jsp
            We need to paste database url in this page.
            We can see all data in here.
         */

        bookRepository.deleteAll();

        Book book1 = new Book("Domain Driven Design", "123", "RandomHouse");
        Book savedBook = bookRepository.save(book1);
        log.info("{} saved with {} id", savedBook.getName(), savedBook.getId());

        Book book2 = new Book("Spring In Action", "1234", "Oriely");
        Book savedBook2 = bookRepository.save(book2);
        log.info("{} saved with {} id", savedBook2.getName(), savedBook2.getId());

        bookRepository.findAll().forEach(book -> {
            log.info("{} founded with {} id", savedBook2.getName(), savedBook2.getId());
        });
    }
}
