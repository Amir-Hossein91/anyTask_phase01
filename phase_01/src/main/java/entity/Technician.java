package entity;

import entity.enums.TechnicianStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.File;
import java.util.List;

@Entity
@DiscriminatorValue("Technician")

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Technician extends Person{
    private long credit;
    @ManyToMany(mappedBy = "technicians")
    private List<SubAssistance> subAssistances;
    private int score;
    private TechnicianStatus technicianStatus;
    private File image;
    private int numberOfFinishedTasks;
    private boolean isActive;
}
