package net.kang.controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("music")
public class MusicController {

    @RequestMapping("main")
    public String Main(Model model) {

        return "main/main";
    }

    @RequestMapping("list")
    public String List(Model model) {

        return "list/list";
    }

    @RequestMapping("friendList")
    public String friendList(Model model) {

        return "friendList/friendList";
    }

    @RequestMapping("login")
    public String login(Model model) {

        return "login/login";
    }

}
