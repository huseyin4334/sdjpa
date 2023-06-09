package guru.springframework.sdjpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor // We have to define no args constructor
@Entity //That's jpa entity api for connect object to table
@Data
public class Book {

    /*
        Generation Types
        1.AUTO -> JPA automatically manage id with this generator
        2.IDENTITY
        3.SEQUENCE -> Database sequences. We can create sequence generator and say to use this generator
        4.TABLE -> We can use for table generation. We can create table and column (TableGenerator). We can give this generator to generatedValue to generator.

        For more: https://www.baeldung.com/hibernate-identifiers
     */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String isbn;
    private String publisher;

    @Setter
    @Getter
    private Long authorId;

    public Book(String name, String isbn, String publisher, Long authorId) {
        this.name = name;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authorId = authorId;
    }

    // COMPARE DIFFERENT OBJECTS WITH ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
