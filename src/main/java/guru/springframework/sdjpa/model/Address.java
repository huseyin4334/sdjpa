package guru.springframework.sdjpa.model;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String address;
    private String city;
    private String state;
    private String zipCode;
}
