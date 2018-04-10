package net.kang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kang.domain.Music;
import net.kang.service.MusicService;


@Controller
public class GuestController {
	@Autowired MusicService musicService;

	@RequestMapping({"/", "guest/index"})
    public String index(Model model) {
	 	List<Music> list = musicService.findAll();
	 	model.addAttribute("list",list);
        return "guest/index";
    }

    @RequestMapping("guest/login")
    public String login() {
        return "guest/login";
    }

    @RequestMapping("guest/sign")
    public String sign() {
        return "guest/sign";
    }



}
