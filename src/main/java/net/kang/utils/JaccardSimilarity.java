package net.kang.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

import net.kang.domain.Music;

public class JaccardSimilarity {
	public static double jaccardCalcular(List<Music> music01, List<Music> music02) {
		Set<Music> musicSet01 = new HashSet<Music>(music01);
		Set<Music> musicSet02 = new HashSet<Music>(music02);

		Set<Music> intersection = Sets.intersection(musicSet01, musicSet02);
		Set<Music> union = Sets.union(musicSet01, musicSet02);

		return ((double) intersection.size() / union.size());
	}
}
