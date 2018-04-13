package net.kang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.kang.getModel.UserMusicTableRow;
import net.kang.service.LinkService;
import net.kang.service.MusicService;

@Controller
public class UserController {
	@Autowired MusicService musicService;
	@Autowired LinkService linkService;
    @RequestMapping({"user/index", "music/main"})
    public String index(Model model) {
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
    	List<UserMusicTableRow> list = musicService.getUserMusicTableRow(userId);
    	model.addAttribute("list", list);
        return "user/index";
    }

    @RequestMapping("user/like")
    public String musicLike(Model model, @RequestParam("mId") int mId, @RequestParam("work") String work) {
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
		switch(work) {
		case "insert" :
			musicService.insertMusicList(userId, mId);
			linkService.insertLink(userId, mId);
			break;
		case "delete" :
			musicService.deleteMusicList(userId, mId);
			linkService.deleteLink(userId, mId);
			break;
		}
		return "redirect:index";
    }
}
