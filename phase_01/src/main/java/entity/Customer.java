package entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Digits;

@Entity
@DiscriminatorValue("Customer")

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends Person {
    @Range(min = 0, message = "Credit can not be negative")
    private long credit;
}
