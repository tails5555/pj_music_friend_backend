package net.kang.getModel;

import lombok.Data;

@Data
public class GraphNode {
	int id;
	String label;
	public GraphNode(int id, String label) {
		this.id=id;
		this.label=label;
	}
}
