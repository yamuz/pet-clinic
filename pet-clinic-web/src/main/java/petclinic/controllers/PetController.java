package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {

    @RequestMapping({"pets","pets/index","pets/index.html"})
    public String indexFun(Model model){
        //model.addAttribute("pets", )
        return "pets/index";
    }
}
