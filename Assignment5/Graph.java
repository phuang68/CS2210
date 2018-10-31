import java.util.Iterator;

/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
public class Graph implements GraphADT {
	private Edge G[][];
	private Node N[];

	public Graph(int n) {
		G = new Edge[n][n];// Create a dimentional Edge array;
		N = new Node[n];// Create a Node array for getNode method

		// Store every possible edges with null value type to indicate there's no edge
		// between the edges

		for (int i = 0; i < n; i++) {
			N[i] = new Node(i);// Store the possible nodes in the Node array
			N[i].setMark(false);// The nodes in the Node array is not marked yet
			for (int j = 0; j < n; j++) {
				G[i][j] = new Edge(N[i], N[j], null);
				G[j][i] = new Edge(N[j], N[i], null);
			}
		}
	}

	/*
	 * Adds to the graph an edge connecting the given vertices. The type of the edge
	 * is as indicated. The label of the edge is set to the empty String. Throws a
	 * GraphException if either node does not exist or if the edge is already in the
	 * graph.
	 */
	public void insertEdge(Node u, Node v, String edgeType) throws GraphException {
		// check if the node are valid
		if (u.getName() >= G[0].length || u.getName() < 0 || v.getName() >= G[0].length || v.getName() < 0)
			throw new GraphException("The Node is invalid");
		// check if the edge is already in the graph
		else if (G[u.getName()][v.getName()].getType() != null || G[v.getName()][u.getName()].getType() != null)
			throw new GraphException("The Edge is alredy there");
		// check if the edge is valid
		else if (u.getName() == v.getName())
			throw new GraphException("The Edge is invalid");
		else {// add the Edge from both sides of the adjacency matrix
			G[u.getName()][v.getName()] = new Edge(u, v, edgeType);
			G[v.getName()][u.getName()] = new Edge(v, u, edgeType);
		}
	}

	/*
	 * Returns the node with the specified name. Throws a GraphException if the node
	 * does not exist.
	 */
	public Node getNode(int name) throws GraphException {
		// check if the node is in the Graph
		// search from the Node array
		for (int i = 0; i < N.length; i++)
			if (N[i].getName() == name)
				return N[i];
		// if after the search doesn't return a node, it means the node does not exist
		throw new GraphException("The Node does not exist");
	}

	/*
	 * Returns a Java Iterator storing all the edges incident on the specified node.
	 * It returns null if the node does not have any edges incident on it. Throws a
	 * GraphException if the node does not exist.
	 */
	public Iterator incidentEdges(Node u) throws GraphException {
		// check if the node exists in the graph
		if (this.getNode(u.getName()).equals(null))
			throw new GraphException("The Node does not exist");
		else {
			// create a stack to store the incident edges
			ArrayStack<Edge> IE = new ArrayStack<Edge>();
			// go through the edge array of node u and find the incident edges
			for (int i = 0; i < G[0].length; i++)
				if (u.getName() == i) {
					for (int j = 0; j < G[i].length; j++)
						if (G[i][j].getType() != null)
							IE.push(G[i][j]);
				}
			// if there's no edges incident to node u, return null
			if (IE.isEmpty())
				return null;
			else
				return IE.iterator();
		}

	}

	/*
	 * Returns the edge connecting the given vertices. Throws a GraphException if
	 * there is no edge conencting the given vertices or if u or v do not exist.
	 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		// check if the nodes are valid
		if (u.getName() > G[0].length || u.getName() < 0 || v.getName() > G[0].length || v.getName() < 0)
			throw new GraphException("The Node is invalid");
		// check if the Edge is in the graph
		else if (G[u.getName()][v.getName()].getType() == null || G[v.getName()][u.getName()].getType() == null)
			throw new GraphException("The Edge is not in the graph");
		else
			return G[u.getName()][v.getName()];
	}

	/*
	 * Returns true is u and v are adjacent, and false otherwise. It throws a
	 * GraphException if either vertex does not exist.
	 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		// check if the nodes are valid
		if (u.getName() > G[0].length || u.getName() < 0 || v.getName() > G[0].length || v.getName() < 0)
			throw new GraphException("The Node is invalid");
		// check if they are adjacent
		else if (G[u.getName()][v.getName()].getType() != null || G[v.getName()][u.getName()].getType() != null)
			return true;
		else
			return false;
	}
}
