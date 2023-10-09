package entity;

import entity.base.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
@EqualsAndHashCode(callSuper = true)
public class SubService extends BaseEntity {
    private String title;
    private long basePrice;
    @OneToMany
    private List<Technician> technicians;
}
