package petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.services.OwnerService;

@RequestMapping("/owner")
@Controller
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/index","/index.html"})
    public String indexFun(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owner/index";
    }
}
