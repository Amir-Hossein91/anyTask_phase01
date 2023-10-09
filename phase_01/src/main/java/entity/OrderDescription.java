package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderDescription extends BaseEntity {
    @OneToOne
    private Order order;
    private long customerSuggestedPrice;
    private LocalDate customerDesiredDateAndTime;
    private String taskDetails;
    private String address;
}
