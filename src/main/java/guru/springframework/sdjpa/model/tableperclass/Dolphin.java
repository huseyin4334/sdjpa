package guru.springframework.sdjpa.model.tableperclass;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Dolphin extends Mammal {

    private Boolean hasSpots;
}
