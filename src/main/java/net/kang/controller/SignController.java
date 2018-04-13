package net.kang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.kang.postModel.UserForm;
import net.kang.service.UserService;


@Controller
@RequestMapping("guest")
public class SignController {
	@Autowired 	UserService userService;
    @RequestMapping(value="create", method=RequestMethod.GET)
    public String create(Model model) {
    	model.addAttribute("userForm", new UserForm());
        return "guest/sign";
    }

    @RequestMapping(value="create", method=RequestMethod.POST)
    public String create(UserForm userForm, Model model) {
    	userService.save(userForm);
        return "redirect:index" ;
    }


}
