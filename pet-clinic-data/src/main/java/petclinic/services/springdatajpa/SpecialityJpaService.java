package petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.model.Speciality;
import petclinic.repositories.SpecialityRepository;
import petclinic.services.SpecialityService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class SpecialityJpaService implements SpecialityService {
    private SpecialityRepository specialityRepository;

    public SpecialityJpaService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality findById(Long aLong) {
        return specialityRepository.findById(aLong).orElse(null);
    }

    @Override
    public Speciality save(Speciality object) {
        return specialityRepository.save(object);
    }

    @Override
    public Set<Speciality> findAll() {
        Set<Speciality> set = new HashSet<>();
        specialityRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public void delete(Speciality object) {
       specialityRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
       specialityRepository.deleteById(aLong);
    }
}
