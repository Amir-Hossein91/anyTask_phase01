package entity.base;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@ToString
@EqualsAndHashCode
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "id_generator")
    private long id;
}
