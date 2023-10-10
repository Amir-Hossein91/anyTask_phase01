package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class SubAssistance extends BaseEntity {
    @NotNull(message = "Sub-assistance title can not be null")
    private String title;
    @Range(min = 0, message = "Base price can not be negative")
    private long basePrice;
    @ManyToMany
    private List<Technician> technicians;
    @ManyToOne
    private Assistance assistance;
    @NotNull(message = "Sub-assistance should have some descriptions")
    private String about;
}
