package petclinic.services.springdatajpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import petclinic.model.Visit;
import petclinic.repositories.VisitRepository;
import petclinic.services.VisitService;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class VisitJpaService implements VisitService {
    private VisitRepository visitRepository;

    public VisitJpaService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public Visit findById(Long aLong) {
        return visitRepository.findById(aLong).orElse(null);
    }

    @Override
    public Visit save(Visit object) {
        return visitRepository.save(object);
    }

    @Override
    public Set<Visit> findAll() {
        Set<Visit> set = new HashSet<>();
        visitRepository.findAll().forEach(set::add);
        return set;
    }

    @Override
    public void delete(Visit object) {

        visitRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {

        visitRepository.deleteById(aLong);
    }
}
