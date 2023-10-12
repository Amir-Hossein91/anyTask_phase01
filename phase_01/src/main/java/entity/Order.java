package entity;

import entity.base.BaseEntity;
import entity.enums.OrderStatus;
import jdk.jfr.Timestamp;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    @ManyToOne
    private SubAssistance subAssistance;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Technician technician;
    private LocalDateTime orderRegistrationDateAndTime;
    @OneToOne
    @Cascade(value = org.hibernate.annotations.CascadeType.PERSIST)
    private OrderDescription orderDescription;
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;
    @Range(min = 1, max = 5, message = "Technician score should be between 1 and 5")
    private int technicianScore;
    @OneToMany(mappedBy = "order")
    @Cascade(value = org.hibernate.annotations.CascadeType.MERGE)
    private List<TechnicianSuggestion> technicianSuggestions;
    private String techEvaluation;



}
