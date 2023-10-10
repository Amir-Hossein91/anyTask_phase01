package entity;

import entity.enums.TechnicianStatus;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.io.File;
import java.util.List;

@Entity
@DiscriminatorValue("Technician")

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Technician extends Person{
    private long credit;
    @ManyToMany(mappedBy = "technicians")
    private List<SubAssistance> subAssistances;
    private int score;
    private TechnicianStatus technicianStatus;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;
    private int numberOfFinishedTasks;
    private boolean isActive;
}
