package net.kang.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.kang.domain.Music;
import net.kang.domain.StandardDeviation;
import net.kang.domain.User;
import net.kang.getModel.GuestMusicTableRow;
import net.kang.getModel.UserMusicTableRow;
import net.kang.repository.MusicRepository;
import net.kang.repository.StandardDeviationRepository;
import net.kang.repository.UserRepository;

@Service
public class MusicService {
	@Autowired UserRepository userRepository;
	@Autowired MusicRepository musicRepository;
	@Autowired StandardDeviationRepository standardDeviationRepository;

	public List<GuestMusicTableRow> getGuestMusicTableRow(){
		List<GuestMusicTableRow> row = new ArrayList<GuestMusicTableRow>();
		List<Music> musicList=musicRepository.findByOrderByPopulateScoreDesc();
		StandardDeviation sd=standardDeviationRepository.findTopByOrderByLatestDateDesc();
		for(int k=0;k<musicList.size();k++) {
			GuestMusicTableRow newRow=new GuestMusicTableRow();
			Music music=musicList.get(k);
			newRow.setRank(k+1);
			newRow.setRate((int)(music.getPopulate().getScore()-sd.getAverage()));
			newRow.setSinger(music.getSinger());
			newRow.setTitle(music.getTitle());
			newRow.setAlbum(music.getAlbum());
			row.add(newRow);
		}
		return row;
	}

	public List<UserMusicTableRow> getUserMusicTableRow(String userId){
		User user = userRepository.findOneByUserId(userId);
		if(user.equals(new User())) return new ArrayList<UserMusicTableRow>();
		List<UserMusicTableRow> row = new ArrayList<UserMusicTableRow>();
		List<Music> musicList=musicRepository.findByOrderByPopulateScoreDesc();
		StandardDeviation sd=standardDeviationRepository.findTopByOrderByLatestDateDesc();
		for(int k=0;k<musicList.size();k++) {
			UserMusicTableRow newRow=new UserMusicTableRow();
			Music music=musicList.get(k);
			if(user.getPlayList().contains(music)) {
				newRow.setLiked(true);
			}
			newRow.setRank(k+1);
			newRow.setRate((int)(music.getPopulate().getScore()-sd.getAverage()));
			newRow.setSinger(music.getSinger());
			newRow.setTitle(music.getTitle());
			newRow.setAlbum(music.getAlbum());
			row.add(newRow);
		}
		return row;
	}

	public List<Music> findAll(){
		return musicRepository.findAll();

	}


}
