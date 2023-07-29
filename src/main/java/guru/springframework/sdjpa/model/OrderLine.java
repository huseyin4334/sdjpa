package guru.springframework.sdjpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
public class OrderLine extends BaseEntity {

    private Integer orderedQuantity;

    @ManyToOne
    private OrderHeader orderHeader;

    @ManyToOne
    private Product product;

    public OrderLine() {
        super();
    }
}
