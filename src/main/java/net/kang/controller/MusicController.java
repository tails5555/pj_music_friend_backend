package net.kang.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kang.domain.Music;
import net.kang.service.MusicService;

@Controller
@RequestMapping("music")
public class MusicController {

	@Autowired MusicService musicService;


    @RequestMapping("main")
    public String Main(Model model) {

    	List<Music> list = musicService.findAll();
    	model.addAttribute("list", list);
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
