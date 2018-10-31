/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class Edge {
	private Node u, v;
	private String type, label = "";

	public Edge(Node u, Node v, String type) {
		this.u = u;
		this.v = v;
		this.type = type;
	}

	// This method returns the type of the edge.
	public String getType() {
		return type;
	}

	// This method sets the type of the edge to the specified value.
	public void setType(String type) {
		this.type = type;
	}

	// This method returns the first endpoint of the edge.
	public Node firstEndpoint() {
		return u;
	}

	// This method returns the second endpoint of the edge.
	public Node secondEndpoint() {
		return v;
	}

	// This method returns the label of the edge
	public String getLabel() {
		return label;
	}

	// This method set the label of the edge
	public void setLabel(String label) {
		this.label = label;
	}

}
