package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "person_sequence")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Person_Roll",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("No roll")

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Person extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private LocalDate registrationDate;
    private boolean isManager;
    private boolean isCustomer;
    private boolean isTechnician;
}
