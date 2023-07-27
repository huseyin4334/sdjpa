package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NamedQuery(name = "author_find_all", query = "from Author")
@NamedQueries({
        @NamedQuery(name = "author_getByName", query = "from Author a where concat(a.firstName, ' ', a.lastName) = :name"),
        @NamedQuery(name = "Author.findByNameWithNameQuery", query = "from Author a where concat(a.firstName, ' ', a.lastName) = :name")
})
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Transient
    private List<Book> books;
}
