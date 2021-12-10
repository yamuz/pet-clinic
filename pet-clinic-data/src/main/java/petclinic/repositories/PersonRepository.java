package petclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import petclinic.model.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {
}
