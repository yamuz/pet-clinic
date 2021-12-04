package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.*;
import petclinic.services.OwnerService;
import petclinic.services.PetTypeService;
import petclinic.services.SpecialityService;
import petclinic.services.VetService;

import java.time.LocalDate;


@Component
public class BootLoader implements CommandLineRunner {

    public final OwnerService ownerService;
    public final VetService vetService;
    public final PetTypeService petTypeService;
    public final SpecialityService specialityService;

    public BootLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args)  {
        int count = petTypeService.findAll().size();
        if (count==0)
            loadData();

    }

    private void loadData() {
        //PET TYPE
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDogType = petTypeService.save(dog);
        PetType cat = new PetType();
        dog.setName("cat");
        PetType savedCatType = petTypeService.save(cat);

        //OWNERS
        Owner ownerAlmaz = new Owner();
        ownerAlmaz.setFirstName("almaz");
        ownerAlmaz.setLastName("abdray");
        ownerAlmaz.setAddress("Remizovka 12");
        ownerAlmaz.setCity("Almaty");
        ownerAlmaz.setTelephone("8708243249384");
        Pet pet1 = new Pet();
        pet1.setPetType(savedDogType);
        pet1.setOwner(ownerAlmaz);
        pet1.setName("Rocco");
        pet1.setBirthDate( LocalDate.of(2012, 2 ,1));
        ownerAlmaz.getPets().add(pet1);
        ownerService.save(ownerAlmaz);

        Owner ownerAlima = new Owner();
        ownerAlima.setFirstName("alima");
        ownerAlima.setLastName("abdray");
        ownerAlima.setAddress("arbat 12");
        ownerAlima.setCity("Moscow");
        ownerAlima.setTelephone("5465765767");
        Pet alimasCat = new Pet();
        alimasCat.setPetType(savedCatType);
        alimasCat.setName("Zoska");
        alimasCat.setBirthDate(LocalDate.of(2018, 2, 7));
        alimasCat.setOwner(ownerAlima);
        ownerAlima.getPets().add(alimasCat);
        ownerService.save(ownerAlima);
        System.out.println("Loaded owners...");

        //SPECIALITIES
        Speciality specialityRadiology = new Speciality();
        specialityRadiology.setDescription("Radiology");
        Speciality radiologySaved = specialityService.save(specialityRadiology);
        Speciality specialityDentistry = new Speciality();
        specialityDentistry.setDescription("Dentistry");
        Speciality dentistrySaved = specialityService.save(specialityDentistry);
        Speciality specialitySurgery = new Speciality();
        specialitySurgery.setDescription("Surgery");
        Speciality surgerySaved = specialityService.save(specialitySurgery);

        Vet vet1 = new Vet();
        vet1.setFirstName("Orhan");
        vet1.setLastName("pamuk");
        vet1.getSpecialities().add(radiologySaved);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Selim");
        vet2.setLastName("Yamuz");
        vet2.getSpecialities().add(dentistrySaved);
        vetService.save(vet2);
        System.out.println("loaded vets...");
    }
}
