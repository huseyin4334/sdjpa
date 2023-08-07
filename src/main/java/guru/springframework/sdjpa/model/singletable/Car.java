package guru.springframework.sdjpa.model.singletable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("car")
public class Car extends Vehicle {

    private String trimLevel;
}
