package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import petclinic.model.Vet;
import petclinic.services.VetService;

import java.util.Set;

@Controller
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"/vets","/vets/index","/vets/index.html","/vets.html"})
    public String indexFun(Model model){

        model.addAttribute("vets", vetService.findAll());
        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsApi() {

        return vetService.findAll();
    }
}
