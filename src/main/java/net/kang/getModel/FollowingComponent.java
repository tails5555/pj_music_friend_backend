package net.kang.getModel;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import net.kang.domain.Music;
@Data
public class FollowingComponent implements Comparable<FollowingComponent>{
	int id;
	int score;
	String name;
	List<Music> playList;
	LocalDateTime followingDate;
	@Override
	public int compareTo(FollowingComponent fingc) {
		if(score>fingc.getScore()) return -1;
		else if(score<fingc.getScore()) return 1;
		return 0;
	}
}
