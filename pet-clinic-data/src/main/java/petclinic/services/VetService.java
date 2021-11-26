package petclinic.services;

import petclinic.model.Pet;
import petclinic.model.Vet;

import java.util.Set;

public interface VetService extends CrudService<Vet, Long> {
    Vet findById(Long id);
    Vet save(Pet owner);
    Set<Vet> findAll();
}
