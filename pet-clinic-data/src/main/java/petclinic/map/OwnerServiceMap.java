package petclinic.map;

import org.springframework.stereotype.Service;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.services.OwnerService;
import petclinic.services.PetService;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long>  implements OwnerService {

    private PetServiceMap petService;
    private PetTypeMapService petTypeService;

    public OwnerServiceMap(PetServiceMap petService, PetTypeMapService petTypeService) {
        this.petService = petService;
        this.petTypeService = petTypeService;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
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
