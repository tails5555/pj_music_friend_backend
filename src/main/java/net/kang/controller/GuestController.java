package net.kang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import net.kang.getModel.GuestMusicTableRow;
import net.kang.service.MusicService;
import net.kang.service.UserService;


@Controller
public class GuestController {
	@Autowired MusicService musicService;
	@Autowired UserService userService;
	@RequestMapping({"/", "guest/index"})
    public String index(Model model) {
	 	List<GuestMusicTableRow> musicList = musicService.getGuestMusicTableRow();
	 	model.addAttribute("list", musicList);
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
