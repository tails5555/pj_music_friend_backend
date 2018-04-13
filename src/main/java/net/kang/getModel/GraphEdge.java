package net.kang.getModel;

import lombok.Data;

@Data
public class GraphEdge {
	int from;
	int to;
	int value;
	String title;
	public GraphEdge() {

	}
	public GraphEdge(int from, int to, int value, String title) {
		this.from=from;
		this.to=to;
		this.value=value;
		this.title=title;
	}
}
