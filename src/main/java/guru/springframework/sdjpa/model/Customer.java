package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Basic(optional = false)
    @Length(max = 50, message = "CustomerName can not be taller than 50 character")
    private String customerName;

    @Valid // control class validations
    @Embedded
    private Address address;

    @Length(max = 20)
    private String phone;

    @Size(max = 255)
    @Email
    private String email;

    @Embedded
    private Audit audit;

    @OneToMany(mappedBy = "customer")
    private Set<OrderHeader> orderHeaders;

    @Version
    private Integer version;

    public Customer() {
        super();
    }
}
