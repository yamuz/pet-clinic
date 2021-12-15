package petclinic.services.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import petclinic.model.Owner;
import petclinic.services.PetService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    private final Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetMapService(), new PetTypeMapService());
        ownerMapService.save(Owner.builder().id(1L).lastName("abdray").build());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName("abdray");
        assertEquals("abdray", owner.getLastName());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(1L);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void save() {
        Owner owner = ownerMapService.findById(ownerId);
        Owner ownerSaved = ownerMapService.save(owner);
        assertEquals(ownerId, ownerSaved.getId());
    }

    @Test
    void findAll() {
        int count = ownerMapService.findAll().size();
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        int count = ownerMapService.findAll().size();
        assertEquals(0, count);

    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(ownerId);
        int count = ownerMapService.findAll().size();
        assertEquals(0, count);
    }
}