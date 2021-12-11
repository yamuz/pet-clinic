package petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.model.Pet;
import petclinic.repositories.PetRepository;
import petclinic.services.PetService;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetJpaService implements PetService {

    private PetRepository petRepository;

    public PetJpaService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Override
    public Pet findById(Long id) {
        return petRepository.findById(id).orElse(null);
    }

    @Override
    public Pet save(Pet pet) {
        return petRepository.save(pet);
    }

    @Override
    public Set<Pet> findAll() {
        Set<Pet> set = new HashSet<>();
        petRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public void delete(Pet object) {
        petRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
       petRepository.deleteById(aLong);
    }
}
