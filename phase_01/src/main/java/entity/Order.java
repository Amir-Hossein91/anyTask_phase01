package entity;

import entity.base.BaseEntity;
import entity.enums.OrderStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
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
    private OrderStatus orderStatus;
    private int technicianScore;
    @OneToMany(mappedBy = "order")
    private List<TechnicianSuggestion> technicianSuggestions;
    private String techEvaluation;



}
