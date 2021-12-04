package petclinic.map;

import org.springframework.stereotype.Service;
import petclinic.model.Speciality;
import petclinic.model.Vet;
import petclinic.services.SpecialityService;
import petclinic.services.VetService;

import java.util.Set;

@Service
public class VetServiceMap extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetServiceMap(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet speciality) {
        if (speciality.getSpecialities().size()>0)
            speciality.getSpecialities().forEach(specialityLoop->{
                if (specialityLoop.getId()== null) {
                    Speciality savedSpecialty = specialityService.save(specialityLoop);
                    speciality.setId(savedSpecialty.getId());
                }

            });
        return super.save( speciality);
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
         super.deleteById(id);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }
}
