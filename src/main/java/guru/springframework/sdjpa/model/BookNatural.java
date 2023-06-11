package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Entity
@Data
public class BookNatural {

    @Id
    @Column(nullable = false, unique = true, updatable = false)
    private String title;

    private String isbn;
    private String publisher;
    private Long authorId;

    public BookNatural(String title, String isbn, String publisher, Long authorId) {
        this.title = title;
        this.isbn = isbn;
        this.publisher = publisher;
        this.authorId = authorId;
    }

    // COMPARE DIFFERENT OBJECTS WITH ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookNatural book = (BookNatural) o;

        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
