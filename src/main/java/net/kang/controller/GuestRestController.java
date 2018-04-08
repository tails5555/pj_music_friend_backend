package net.kang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kang.domain.Music;
import net.kang.repository.MusicRepository;
@RestController
@CrossOrigin
@RequestMapping("/guest")
public class GuestRestController {
	@Autowired MusicRepository musicRepository;

	@GetMapping("/music/findAll")
	public ResponseEntity<List<Music>> findAll(){
		return new ResponseEntity<List<Music>>(musicRepository.findAll(), HttpStatus.OK);
	}
}
