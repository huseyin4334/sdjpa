package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
public class Customer extends BaseEntity {

    private String customerName;

    @Embedded
    private Address address;

    private String phone;

    private String email;

    @Embedded
    private Audit audit;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orderHeaders;

    public Customer() {
        super();
    }
}
