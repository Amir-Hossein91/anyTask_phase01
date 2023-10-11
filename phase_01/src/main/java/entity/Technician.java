package entity;

import entity.enums.TechnicianStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("Technician")

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Technician extends Person{
    @Range(min = 0, message = "Credit can not be negative")
    private long credit;
    @ManyToMany(mappedBy = "technicians")
    private List<SubAssistance> subAssistances;
    private int score;
    @Enumerated(value = EnumType.STRING)
    private TechnicianStatus technicianStatus;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    @Range(min = 0, message = "Number of finished tasks can not be negative")
    private int numberOfFinishedTasks;
    private boolean isActive;

    public String toString() {
        return super.toString() +
                ", score = " + this.getScore() +
                ", technicianStatus = " + this.getTechnicianStatus() +
                ", numberOfFinishedTasks = " + this.getNumberOfFinishedTasks() +
                ", isActive = " + this.isActive();
    }
}
