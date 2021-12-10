package petclinic.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{
    private Date localDate;
    private String description;

    @ManyToOne
    @JoinColumn(name="pet_id")
    private Pet pet;

    public Date getLocalDate() {
        return localDate;
    }

    public void setLocalDate(Date localDate) {
        this.localDate = localDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
