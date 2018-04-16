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
    public String index(Model model) { // 현재 즐겨찾기한 음악 목록을 포함해서 보여줌
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
    	List<UserMusicTableRow> list = musicService.getUserMusicTableRow(userId);
    	model.addAttribute("list", list);
        return "user/index";
    }

    @RequestMapping("user/like") // 사용자가 음악 목록에서 즐겨찾기를 할 때 링킹 추가와 인기 점수 추가로 더해줌
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
