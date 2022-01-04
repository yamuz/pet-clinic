package petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import petclinic.model.Owner;
import petclinic.services.OwnerService;

import java.util.List;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @RequestMapping({"","/index","/index.html"})
    public String indexFun(Model model){
        model.addAttribute("owners", ownerService.findAll());
        return "owners/index";
    }

    @RequestMapping("/find")
    public String findOwner(Model model){
        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping("/{ownerId}")
    public ModelAndView getOwner(@PathVariable Long ownerId){

        ModelAndView modelAndView = new ModelAndView("owners/ownerDetails");
        modelAndView.addObject(ownerService.findById(ownerId));
        return modelAndView;
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if (owner.getLastName() == null)
            owner.setLastName("");

        List<Owner> ownerList = ownerService.findAllByLastNameLike("%" + owner.getLastName() + "%");
        if (ownerList.isEmpty()){
            result.rejectValue("lastName", "notfound", "not found");
            return "owners/findOwners";
        } else if(ownerList.size()==1){
            owner = ownerList.get(0);
            return "redirect:/owners/" + owner.getId();
        } else {
            model.addAttribute("selections", ownerList);
            return "owners/ownerslist";
        }

    }
}
