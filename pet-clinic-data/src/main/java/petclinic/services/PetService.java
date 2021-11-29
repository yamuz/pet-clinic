package petclinic.services;

import petclinic.model.Pet;

import java.util.Set;

public interface PetService extends CrudService<Pet, Long>{
    Pet findById(Long id);
    Pet save(Pet owner);
    Set<Pet> findAll();
}
