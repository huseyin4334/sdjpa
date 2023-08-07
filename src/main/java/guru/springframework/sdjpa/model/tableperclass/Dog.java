package guru.springframework.sdjpa.model.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Dog extends Mammal {

    private String breed;
}
