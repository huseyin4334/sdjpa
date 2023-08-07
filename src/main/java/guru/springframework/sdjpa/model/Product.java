package guru.springframework.sdjpa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product", indexes = {
        @Index(name = "name_search", columnList = "name"),
        @Index(name = "quantityOnHand_search", columnList = "quantity_on_hand")
})

/*
    For more:
        https://www.baeldung.com/jpa-indexes
        https://dzone.com/refcardz/getting-started-with-hibernate
 */
public class Product extends BaseEntity {

    private String name;


    @ManyToMany
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

    private Integer quantityOnHand = 0;
}
