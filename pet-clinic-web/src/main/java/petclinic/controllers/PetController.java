package petclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PetController {

    @RequestMapping({"","/","pets/index","pets/index.html"})
    public String indexFun(){
        return "index";
    }
}
