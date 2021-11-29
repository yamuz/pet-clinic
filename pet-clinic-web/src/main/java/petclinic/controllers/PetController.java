package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {

    @RequestMapping({"pet","pet/index","pet/index.html"})
    public String indexFun(Model model){
        //model.addAttribute("pets", )
        return "pet/index";
    }
}
