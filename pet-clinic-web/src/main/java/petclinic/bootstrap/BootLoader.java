package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.Owner;
import petclinic.model.Pet;
import petclinic.model.PetType;
import petclinic.model.Vet;
import petclinic.services.OwnerService;
import petclinic.services.PetTypeService;
import petclinic.services.VetService;

import java.time.LocalDate;
import java.util.HashSet;


@Component
public class BootLoader implements CommandLineRunner {


    public final OwnerService ownerService;
    public final VetService vetService;
    public final PetTypeService petTypeService;

    public BootLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args)  {

        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);
        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("almaz");
        owner1.setLastName("abdray");
        owner1.setAddress("Remizovka 12");
        owner1.setCity("Almaty");
        owner1.setTelephone("8708243249384");
        //owner1.setPets(new HashSet<Pet>(cat));
        ownerService.save(owner1);
        
        Owner owner2 = new Owner();
        owner2.setFirstName("alima");
        owner2.setLastName("abdray");
        owner2.setAddress("arbat 12");
        owner2.setCity("Moscow");
        owner2.setTelephone("5465765767");
        ownerService.save(owner2);
        System.out.println("Loaded owners...");

        Vet vet1 = new Vet();
        vet1.setFirstName("Orhan");
        vet1.setLastName("pamuk");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Selim");
        vet2.setLastName("Yamuz");
        vetService.save(vet2);
        System.out.println("loaded vets...");
        
        Pet pet1 = new Pet();
        pet1.setPetType(savedDogType);
        pet1.setOwner(owner2);
        pet1.setName("Rocco");
        pet1.setBirthDate( LocalDate.of(2012, 2 ,1));
        owner1.getPets().add(pet1);

        Pet alimasCat = new Pet();
        alimasCat.setPetType(savedCatType);
        alimasCat.setName("Zoska");
        alimasCat.setBirthDate(LocalDate.of(2018, 2, 7));
        alimasCat.setOwner(owner2);
        owner2.getPets().add(alimasCat);


    }
}
