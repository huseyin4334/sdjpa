package guru.springframework.sdjpa.model.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("truck")
public class Truck extends Vehicle {

    private Integer payload;
}
