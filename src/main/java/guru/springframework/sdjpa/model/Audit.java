package guru.springframework.sdjpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Embeddable
@Data
public class Audit {
    @CreationTimestamp // hibernate feature
    @Column(updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp // hibernate feature
    private Timestamp lastModifiedDate;
}
