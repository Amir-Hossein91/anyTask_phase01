package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDescription extends BaseEntity {
    @Range(min = 0, message = "Price can not be negative")
    private long customerSuggestedPrice;
    @NotNull(message = "Customer desired start date must be set")
    private LocalDateTime customerDesiredDateAndTime;
    @NotNull(message = "Brief descriptions of task should be submitted")
    private String taskDetails;
    @NotNull(message = "Address can not be null")
    private String address;
}
