package petclinic.services.map;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.services.OwnerService;

import java.util.Set;

@Service
@Profile("map")
public class OwnerMapService extends AbstractMapService<Owner, Long>  implements OwnerService {

    private PetMapService petService;
    private PetTypeMapService petTypeService;

    public OwnerMapService(PetMapService petService, PetTypeMapService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return super.findAll()
                .stream()
                .filter(owner -> owner.getLastName()
                .equals(lastName))
                .findFirst().orElse(null);

    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    public Owner save(Owner owner) {
        if (owner!=null){
            if (owner.getPets()!=null) {
                owner.getPets().forEach(pet -> {
                     if (pet.getPetType()!=null) {
                         if (pet.getPetType().getId()==null){
                             pet.setPetType(petTypeService.save(pet.getPetType()));
                         }
                     } else { throw new RuntimeException("pet type is required");}

                     if (pet.getId()==null){
                         Pet petSaved  =petService.save(pet);
                         petSaved.setId(petSaved.getId());
                     }
                });
            }
            return super.save(owner);
        } else
            return null;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


}
