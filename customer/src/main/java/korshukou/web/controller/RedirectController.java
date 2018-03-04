package korshukou.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RedirectController {


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView method() {
        return new ModelAndView("redirect:" + "/swagger-ui.html");

    }
}
