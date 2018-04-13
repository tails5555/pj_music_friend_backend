package net.kang.utils;

import java.util.ArrayList;
import java.util.List;

import net.kang.domain.Music;

public class MakeLabel {
	public static String makeLabel(List<Music> musics) {
		StringBuffer tmp = new StringBuffer();
		List<Music> musicList = new ArrayList<Music>(musics);
		for(int k=0;k<musicList.size();k++) {
			if(k!=musicList.size()-1)
				tmp.append(String.format("%s - %s, ", musicList.get(k).getSinger(), musicList.get(k).getTitle()));
			else
				tmp.append(String.format("%s - %s", musicList.get(k).getSinger(), musicList.get(k).getTitle()));
		}
		return tmp.toString();
	}
}
