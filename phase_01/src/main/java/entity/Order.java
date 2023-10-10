package entity;

import entity.base.BaseEntity;
import entity.enums.OrderStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@SuperBuilder
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Order extends BaseEntity {
    @OneToOne
    private SubAssistance subAssistance;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Technician technician;
    private LocalDate orderRegistrationDateAndTime;
    @OneToOne
    private OrderDescription orderDescription;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Range(min = 1, max = 5, message = "Technician score should be between 1 and 5")
    private int technicianScore;
    @OneToMany(mappedBy = "order")
    private List<TechnicianSuggestion> technicianSuggestions;
    private String techEvaluation;



}
