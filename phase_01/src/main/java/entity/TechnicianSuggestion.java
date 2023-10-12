package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class TechnicianSuggestion extends BaseEntity {
    @OneToOne
    private Technician technician;
    @ManyToOne
    private Order order;
    private LocalDateTime DateAndTimeOfTechSuggestion;
    @Range(min = 0, message = "Price can not be negative")
    private long techSuggestedPrice;
    @NotNull(message = "A technician suggested start date must be set")
    private LocalDateTime techSuggestedDate;
    @Range(min = 0, message = "Task duration can not be negative")
    private int taskEstimatedDuration;
}
