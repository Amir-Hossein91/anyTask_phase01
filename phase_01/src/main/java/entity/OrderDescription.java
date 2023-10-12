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

    public String toString() {
        return "\n\t\t" + super.toString() +
                "\n\t\tcustomer_Suggested_Price = " + this.getCustomerSuggestedPrice() +
                "\n\t\tcustomer_Desired_Date_And_Time = " + this.getCustomerDesiredDateAndTime() +
                "\n\t\ttask_Details = " + this.getTaskDetails() +
                "\n\t\taddress = " + this.getAddress();
    }
}
