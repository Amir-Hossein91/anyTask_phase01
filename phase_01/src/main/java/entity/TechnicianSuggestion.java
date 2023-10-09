package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianSuggestion extends BaseEntity {
    @ManyToOne
    private Order order;
    private LocalDate DateAndTimeOfTechSuggestion;
    private long techSuggestedPrice;
    private LocalDate techSuggestedDate;
    private int taskEstimatedDuration;
}
