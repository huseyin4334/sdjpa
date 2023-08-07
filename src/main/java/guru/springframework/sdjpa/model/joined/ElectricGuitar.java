package guru.springframework.sdjpa.model.joined;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class ElectricGuitar extends Guitar{

    private Integer numberOfPickUp;
}
