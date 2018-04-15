package net.kang.getModel;

import lombok.Data;

@Data
public class RecommendComponent implements Comparable<RecommendComponent>{
	int id;
	int score;
	String name;
	Boolean followed;

	@Override
	public int compareTo(RecommendComponent rc) {
		if(score>rc.getScore()) return -1;
		else if(score<rc.getScore()) return 1;
		return 0;
	}
}
