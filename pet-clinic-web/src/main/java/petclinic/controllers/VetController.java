package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class VetController {

    @RequestMapping({"vet","vet/index","vet/index.html"})
    public String indexFun(Model model){

        return "vet/index";
    }
}
