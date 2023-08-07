package guru.springframework.sdjpa.model;

import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Embeddable
@Data
public class Address {
    private String address;

    @Length(max = 20)
    private String city;
    private String state;
    private String zipCode;
}
