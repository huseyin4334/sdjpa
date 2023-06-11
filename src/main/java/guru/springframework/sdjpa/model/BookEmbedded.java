package guru.springframework.sdjpa.model;

import guru.springframework.sdjpa.model.composite.NameId;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Entity
@Table(name = "author_composite") // that anotation will map this object with BookComposite object in database to same table.
@Data
@IdClass(NameId.class)
public class BookEmbedded {

    @EmbeddedId
    private NameId nameId;

    private String publisher;
    private Long authorId;

    public BookEmbedded(NameId nameId, String publisher, Long authorId) {
        this.nameId = nameId;
        this.publisher = publisher;
        this.authorId = authorId;
    }

    // COMPARE DIFFERENT OBJECTS WITH ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEmbedded book = (BookEmbedded) o;

        return Objects.equals(nameId, nameId);
    }

    @Override
    public int hashCode() {
        return nameId != null ? nameId.hashCode() : 0;
    }
}
