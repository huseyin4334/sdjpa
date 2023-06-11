package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class BookUuid {


    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2") // strategy = "uuid2" is default uuid generator for "rfc uuid 4122".
    @Column(length = 16, columnDefinition = "BINARY(16)", updatable = false, nullable = false)
    @JdbcTypeCode(value = Types.BINARY)
    private UUID id;

    private String isbn;
    private String publisher;
    private String title;
    private Long authorId;

    public BookUuid(String title, String isbn, String publisher, Long authorId) {
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

        BookUuid book = (BookUuid) o;

        return Objects.equals(id, book.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
