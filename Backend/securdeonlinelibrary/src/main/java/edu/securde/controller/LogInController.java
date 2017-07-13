package edu.securde.controller;

/**
 * Created by Danica C. Sevilla on 7/12/2017.
 */
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import edu.securde.model.User;
import edu.securde.service.UserService;

@Controller
public class LogInController {

    @Autowired
    private UserService userService;
    @RequestMapping(value={"/", "/LogIn"}, method = RequestMethod.GET)
    public ModelAndView LogIn(){
        System.out.println("LoginController /Login Hello");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/LogIn");
        return modelAndView;
    }

    @RequestMapping(value="/SignUp", method = RequestMethod.GET)
    public ModelAndView SignUp(){
        System.out.println("SignUpController /SignUp Hello");
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.setViewName("SignUp");
        return modelAndView;
    }

}
