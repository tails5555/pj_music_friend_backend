package net.kang.getModel;

import lombok.Data;

@Data
public class GraphEdge {
	int from;
	int to;
	int value;
	String label;
	String title;
	public GraphEdge() {

	}
	public GraphEdge(int from, int to, String label, int value, String title) {
		this.from=from;
		this.to=to;
		this.label=label;
		this.value=value;
		this.title=title;
	}
}
