package net.kang.getModel;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import net.kang.domain.Music;
@Data
public class FollowerComponent implements Comparable<FollowerComponent>{
	int id;
	int score;
	String name;
	List<Music> playList;
	LocalDateTime followerDate;
	@Override
	public int compareTo(FollowerComponent fc) {
		if(score>fc.getScore()) return -1;
		else if(score<fc.getScore()) return 1;
		return 0;
	}
}
