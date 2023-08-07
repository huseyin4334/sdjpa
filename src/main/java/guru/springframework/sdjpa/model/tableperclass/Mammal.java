package guru.springframework.sdjpa.model.tableperclass;

import jakarta.persistence.*;
import lombok.Data;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@Data
public class Mammal {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Integer bodyTemp;
}
