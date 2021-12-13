package petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{
    private Date localDate;
    private String description;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

    @Builder
    public Visit(Long id, Date localDate, String description, Pet pet) {
        super(id);
        this.localDate = localDate;
        this.description = description;
        this.pet = pet;
    }
}
