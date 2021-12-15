package petclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import petclinic.model.*;
import petclinic.services.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


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
        PetType dog = PetType.builder().name("dog").build();
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = PetType.builder().name("cat").build();
        PetType savedCatType = petTypeService.save(cat);

        //OWNERS
        Set<Pet> petsAlmaz = new HashSet<>();
        Owner ownerAlmaz = Owner.builder().firstName("almaz").lastName("abdray")
                .address("Remizovka 12").city("Almaty").telephone("8708243249384")
                .pets(petsAlmaz).build();

        Pet pet1 = Pet.builder().petType(savedDogType).name("Rocco").owner(ownerAlmaz)
                .birthDate(LocalDate.of(2012, 2 ,1)).build();

        if (ownerAlmaz.getPets()!= null)
            ownerAlmaz.getPets().add(pet1);
        try {
            ownerService.save(ownerAlmaz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Set<Pet> petsAlima = new HashSet<>();
        Owner ownerAlima = Owner.builder().firstName("alima").lastName("abdray")
                .address("arbat 12").city("Moscow").telephone("5465765767")
                .pets(petsAlima).build();


        Pet alimasCat  = Pet.builder().petType(savedCatType).name("Zoska").owner(ownerAlima)
                .birthDate(LocalDate.of(2018, 2, 7)).build();
        ownerAlima.getPets().add(alimasCat);
        ownerService.save(ownerAlima);
        System.out.println("Loaded owners...");

        //SPECIALITIES
        Speciality specialityRadiology = Speciality.builder().description("Radiology").build();
        Speciality radiologySaved = specialityService.save(specialityRadiology);
        Speciality specialityDentistry =Speciality.builder().description("Dentistry").build();
        Speciality dentistrySaved = specialityService.save(specialityDentistry);
        Speciality specialitySurgery = Speciality.builder().description("Surgery").build();
        Speciality surgerySaved = specialityService.save(specialitySurgery);

        //VETS
        Vet vet1 = Vet.builder().firstName("Orhan").lastName("pamuk").build();
        vet1.getSpecialities().add(radiologySaved);
        vetService.save(vet1);

        Vet vet2 = Vet.builder().firstName("Selim").lastName("Yamuz").build();
        vet2.getSpecialities().add(dentistrySaved);
        vetService.save(vet2);
        System.out.println("loaded vets...");

        //VISITS
        Visit visit = Visit.builder().description("visit 1").
                localDate(Date.valueOf(LocalDate.of(2010, 11, 20)))
                .pet(pet1).build();
        visitService.save(visit);

        Visit visit2 = Visit.builder().description("visit 2").
                localDate(Date.valueOf(LocalDate.of(2009, 11, 20)))
                .pet(pet1).build();
        visitService.save(visit2);
        System.out.println("visits saved");

    }
}
