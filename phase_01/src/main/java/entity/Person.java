package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@SequenceGenerator(name = "id_generator", sequenceName = "person_sequence")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Person_Roll",discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("No roll")

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Person extends BaseEntity {
    @Pattern(regexp = "^[^\\d]{3,}$", message = "first name should be at least three characters and " +
            "no digits are allowed")
    private String firstName;
    @Pattern(regexp = "^[^\\d]{3,}$", message = "last name should be at least three characters and " +
            "no digits are allowed")
    private String lastName;
    @Column(unique = true)
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Invalid email address format")
    private String email;
    @Column(unique = true)
    @NotNull(message = "Username can not be null")
    @Pattern(regexp = "^[^\\s]+$", message = "Username can not be empty")
    private String username;
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8}$", message = "Password must be exactly " +
            "8 characters containing digits and letters")
    private String password;
    private LocalDate registrationDate;

    public String toString() {
        return  "firstName = " + this.getFirstName() +
                ", lastName = " + this.getLastName() +
                ", " + super.toString() +
                ", email = " + this.getEmail() +
                ", username = " + this.getUsername() +
                ", registrationDate = " + this.getRegistrationDate();
    }
}
