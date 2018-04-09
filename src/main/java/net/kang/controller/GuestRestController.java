package net.kang.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kang.getModel.GuestMusicTableRow;
import net.kang.service.MusicService;
@RestController
@CrossOrigin
@RequestMapping("/guest")
public class GuestRestController {
	@Autowired MusicService musicService;

	@GetMapping("/music/findAll")
	public ResponseEntity<List<GuestMusicTableRow>> findAll(){
		List<GuestMusicTableRow> musicList=musicService.getGuestMusicTableRow();
		if(musicList.size()>0)
			return new ResponseEntity<List<GuestMusicTableRow>>(musicList, HttpStatus.OK);
		else
			return new ResponseEntity<List<GuestMusicTableRow>>(new ArrayList<GuestMusicTableRow>(), HttpStatus.NO_CONTENT);
	}
}
