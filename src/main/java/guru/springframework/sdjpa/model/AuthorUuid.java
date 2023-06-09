package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;

import java.sql.Types;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
public class AuthorUuid {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JdbcTypeCode(value = Types.VARCHAR)
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    private String firstName;
    private String lastName;

    public AuthorUuid(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
