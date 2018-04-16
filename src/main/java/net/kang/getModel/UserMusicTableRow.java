package net.kang.getModel;

import lombok.Data;

@Data
public class UserMusicTableRow {
	int id;
	int rank;
	int rate;
	String title;
	String singer;
	String album;
	boolean liked;
}
