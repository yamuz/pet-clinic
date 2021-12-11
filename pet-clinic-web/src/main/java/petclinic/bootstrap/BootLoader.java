package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.*;
import petclinic.services.*;

import java.sql.Date;
import java.time.LocalDate;


@Component
public class BootLoader implements CommandLineRunner {

    public final OwnerService ownerService;
    public final VetService vetService;
    public final PetTypeService petTypeService;
    public final SpecialityService specialityService;
    public final VisitService visitService;

    public BootLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
        this.visitService = visitService;
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

        //VISITS
        Visit visit = new Visit();
        visit.setDescription("visit 1");
        visit.setLocalDate(Date.valueOf(LocalDate.of(2010, 11, 20)));
        visit.setPet(pet1);
        visitService.save(visit);

        Visit visit2 = new Visit();
        visit2.setDescription("visit 2");
        visit2.setLocalDate(Date.valueOf(LocalDate.of(2009, 10, 20)));
        visit2.setPet(pet1);
        visitService.save(visit2);
        System.out.println("visits saved");

    }
}
