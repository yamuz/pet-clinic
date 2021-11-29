package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.model.PetType;
import petclinic.model.Vet;
import petclinic.services.OwnerService;
import petclinic.services.VetService;

import java.time.LocalDate;


@Component
public class BootLoader implements CommandLineRunner {


    public final OwnerService ownerService;
    public final VetService vetService;

    public BootLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args)  {

        Owner owner1 = new Owner();
        owner1.setFirstName("almaz");
        owner1.setLastName("abdray");
        owner1.setId(1L);
        ownerService.save(owner1);
        
        Owner owner2 = new Owner();
        owner2.setFirstName("alima");
        owner2.setLastName("abdray");
        owner2.setId(1L);
        ownerService.save(owner2);
        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Orhan");
        vet1.setLastName("pamuk");
        vet1.setId(1L);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Selim");
        vet2.setLastName("Yamuz");
        vet2.setId(2L);
        vetService.save(vet2);
        System.out.println("loaded vets...");
        
        Pet pet = new Pet();
        PetType type = new PetType();
        type.setName("Dog");

        pet.setPetType(type);
        pet.setOwner(owner2);
        pet.setId(1L);
        pet.setBirthDate( LocalDate.of(2012, 2 ,1));

    }
}
