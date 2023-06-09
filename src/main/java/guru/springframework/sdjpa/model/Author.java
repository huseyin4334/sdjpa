package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Author {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
}
