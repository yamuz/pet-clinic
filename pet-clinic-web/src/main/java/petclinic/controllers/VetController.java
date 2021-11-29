package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.services.VetService;

@Controller
public class VetController {

    private VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"vet","vet/index","vet/index.html"})
    public String indexFun(Model model){

        model.addAttribute("vets", vetService.findAll());
        return "vet/index";
    }
}
