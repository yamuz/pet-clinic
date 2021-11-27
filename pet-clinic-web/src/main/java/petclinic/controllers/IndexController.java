package petclinic.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import petclinic.map.OwnerServiceMap;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @Autowired
    OwnerServiceMap ownerServiceMap;

    @RequestMapping({"","/","index","index.html"})
    public String indexFun(){

       // String redirectUrl = request.getScheme() + "://www.yahoo.com";
        //return "redirect:" + redirectUrl;
       return "index";
    }
}
