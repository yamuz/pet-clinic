package petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.model.Vet;
import petclinic.repositories.VetRepository;
import petclinic.services.VetService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VetJpaService implements VetService {
    private VetRepository vetRepository;

    public VetJpaService(VetRepository vetRepository) {
        this.vetRepository = vetRepository;
    }

    @Override
    public Vet findById(Long id) {
        return vetRepository.findById(id).orElse(null);
    }

    @Override
    public Vet save(Vet vet) {
        return vetRepository.save(vet);
    }

    @Override
    public Set<Vet> findAll() {
        Set<Vet> set = new HashSet<>();
        vetRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public void delete(Vet vet) {
        vetRepository.delete(vet);
    }

    @Override
    public void deleteById(Long aLong) {
         vetRepository.deleteById(aLong);
    }
}
