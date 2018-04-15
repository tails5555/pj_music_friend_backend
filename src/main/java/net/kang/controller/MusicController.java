package net.kang.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.kang.service.FollowerService;
import net.kang.service.LinkService;
import net.kang.service.MusicService;
import net.kang.service.UserService;

@Controller
@RequestMapping("music")
public class MusicController {
	@Autowired UserService userService;
	@Autowired MusicService musicService;
	@Autowired LinkService linkService;
	@Autowired FollowerService followerService;

	private String jsonStringFromObject(Object object) throws JsonProcessingException { // 이는 Java에서 쓰인 모든 객체들에 대해서 String JSON으로 반환한다.
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
    }

    @RequestMapping("list") // My 하트 구현. 내가 추가한 음악 목록을 이용해서 목록을 보여준다.
    public String List(Model model) throws JsonProcessingException {
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
    	model.addAttribute("list", musicService.myPlayList(userId));
    	model.addAttribute("nodeList", this.jsonStringFromObject(userService.getGraphNode()));
    	model.addAttribute("edgeList", this.jsonStringFromObject(linkService.getGraphEdges(userId)));
        return "list/list";
    }

    @RequestMapping("like") // My 하트에서 자신이 추가한 음악에 대해서 삭제를 하도록 반영하였음.
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
		return "redirect:list";
    }

    @RequestMapping("friendList")
    public String friendList(Model model) {
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
		model.addAttribute("followers", followerService.getFollowerComponent(userId));
		model.addAttribute("followings", followerService.getFollowingComponent(userId));
		model.addAttribute("recommends", followerService.getRecommendComponent(userId));
        return "friendList/friendList";
    }

    @RequestMapping("follow")
    public String follow(Model model, @RequestParam("subUserId") int subUserId) {
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
		followerService.following(userId, subUserId);
		return "redirect:friendList";
    }

    @RequestMapping("unfollow")
    public String unfollow(Model model, @RequestParam("subUserId") int subUserId) {
    	Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userId=authentication.getName();
		followerService.unfollowing(userId, subUserId);
		return "redirect:friendList";
    }

    @RequestMapping("login")
    public String login(Model model) {

        return "login/login";
    }

}
