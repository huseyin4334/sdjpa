package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor // We have to define no args constructor
@Entity //That's jpa entity api for connect object to table
@Data
@AttributeOverrides({
        @AttributeOverride(
                name = "shippingAddress.address",
                column = @Column(name = "shipping_address")
        ),
        @AttributeOverride(
                name = "shippingAddress.city",
                column = @Column(name = "shipping_city")
        ),
        @AttributeOverride(
                name = "shippingAddress.state",
                column = @Column(name = "shipping_state")
        ),
        @AttributeOverride(
                name = "shippingAddress.zipCode",
                column = @Column(name = "shipping_zip_code")
        ),
        @AttributeOverride(
                name = "billingAddress.address",
                column = @Column(name = "bill_to_address")
        ),
        @AttributeOverride(
                name = "billingAddress.city",
                column = @Column(name = "bill_to_city")
        ),
        @AttributeOverride(
                name = "billingAddress.state",
                column = @Column(name = "bill_to_state")
        ),
        @AttributeOverride(
                name = "billingAddress.zipCode",
                column = @Column(name = "bill_to_zip_code")
        )
})

@Table(name = "order_header")
public class OrderHeader extends BaseEntity {

    @Embedded
    private Address shippingAddress;

    @Embedded
    private Address billingAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Embedded
    @Column(name = "audit")
    private Audit audit;

    @Lob // that's using for large data. clob, blob. For more, https://www.baeldung.com/hibernate-lob
    private String metaValue; // meta_value long_text COLLATE -> mysql

    @OneToMany(mappedBy = "orderHeader", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true, fetch = FetchType.EAGER) // this relation is bidirectional
    @Fetch(FetchMode.SUBSELECT) // we are saying create subquery for fetch this child entity with this annotation.
    private Set<OrderLine> orderLines;

    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private Customer customer;

    public Timestamp getCreatedDate() {
        return audit.getCreatedDate();
    }

    public Timestamp getLastModifiedDate() {
        return audit.getLastModifiedDate();
    }
}
