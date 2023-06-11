package guru.springframework.sdjpa.model.composite;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Entity
@Data
@IdClass(NameId.class)
public class BookComposite {

    @Id
    private String title;

    @Id
    private String isbn;

    private String publisher;
    private Long authorId;

    public BookComposite(String title, String isbn, String publisher, Long authorId) {
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

        BookComposite book = (BookComposite) o;

        return Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }
}
