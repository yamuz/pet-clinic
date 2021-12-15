package petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.services.OwnerService;

@Controller
public class IndexController {

    @RequestMapping({"/index","/index.html"})
    public String indexFun(){

       // String redirectUrl = request.getScheme() + "://www.yahoo.com";
        //return "redirect:" + redirectUrl;
       return "index";
    }

    @RequestMapping("/oups")
    public String oups(){
        return "notimplemented";
    }
}
