package petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.model.PetType;
import petclinic.repositories.PetTypeRepository;
import petclinic.services.PetTypeService;
import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class PetTypeJpaService implements PetTypeService {
    private PetTypeRepository petTypeRepository;

    public PetTypeJpaService(PetTypeRepository petTypeRepository) {
        this.petTypeRepository = petTypeRepository;
    }

    @Override
    public PetType findById(Long aLong) {
        return petTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public PetType save(PetType object) {
        return petTypeRepository.save(object);
    }

    @Override
    public Set<PetType> findAll() {
        Set<PetType> set = new HashSet<>();
        petTypeRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public void delete(PetType object) {
        petTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        petTypeRepository.deleteById(aLong);
    }
}
