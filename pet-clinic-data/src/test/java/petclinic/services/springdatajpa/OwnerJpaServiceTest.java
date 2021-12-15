package petclinic.services.springdatajpa;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.model.PetType;
import petclinic.repositories.OwnerRepository;
import petclinic.repositories.PetTypeRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OwnerJpaServiceTest {
    private final String name = "Almaz";
    private final String lastName = "Abdray";
    private final Long ownerId = 1L;
    private Owner ownerAlmaz;

    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerJpaService ownerJpaService;
    @InjectMocks
    PetTypeJpaService petTypeJpaService;

    private AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable =    MockitoAnnotations.openMocks(this);
        ownerAlmaz = Owner.builder().id(ownerId).lastName(lastName).build();
    }

    /*@AfterEach
    void afterAll() throws Exception{
        autoCloseable.close();
    }*/

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(any())).thenReturn(ownerAlmaz);
        Owner abdray = ownerJpaService.findByLastName(lastName);

        assertEquals("Abdray", abdray.getLastName());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(ownerAlmaz));
        Owner abdray = ownerJpaService.findById(ownerId);

        assertEquals(ownerId, abdray.getId());
    }

    @Test
    void save() {
        Owner ownerToSave = Owner.builder().id(ownerId).build();
        when(ownerRepository.save(any())).thenReturn(ownerAlmaz);

        Owner ownerSaved = ownerJpaService.save(ownerToSave);
        assertNotNull(ownerSaved);
        assertNotNull(ownerSaved.getId());
    }

    @Test
    void findAll() {
        Set<Owner> set = new HashSet<>();
        set.add(Owner.builder().id(1L).build());
        set.add(Owner.builder().id(2L).build());

        when(ownerRepository.findAll()).thenReturn(set);
        int size = ownerJpaService.findAll().size();
        assertEquals(2, size );
    }

    @Test
    void delete() {
        ownerJpaService.delete(ownerAlmaz);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(ownerId);
        verify(ownerRepository).deleteById(any());
    }
}