
/*
 *Course£ºCS2210B
 *Author: Pu Huang
 *Student Number: 250986943
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

public class Labyrinth {
	private Graph G; // Declare graph object
	// Declare integers for the scale, width, length, brick bombs,acid
	// bombs,entrance and exit
	private int scale, width, length, k1, k2, entrance, exit;
	private boolean added;
	private ArrayStack<Node> S = new ArrayStack();// Create a stack to store the nodes in the path;// Declare a
	private Node[] N; // stack to store the nodes in the path
	private Iterator<Edge> P;// Declare a iterator to store the possible path
	private Edge E;// Declare a Edge to store current way
	private Iterator<Edge> nodeEdges;
	private Node curNode, nextNode;
	private Edge tempEdge;
	private boolean done = false;

	public Labyrinth(String inputFile) throws LabyrinthException {
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFile)); // Create a reader from the input file

			scale = Integer.parseInt(input.readLine()); // Set the scale to the first line
			width = Integer.parseInt(input.readLine()); // Set the width to the the second line
			length = Integer.parseInt(input.readLine()); // Set the Length to the third line
			k1 = Integer.parseInt(input.readLine()); // Set number of brick bombs to the fourth line
			k2 = Integer.parseInt(input.readLine()); // Set number of acid bombs to the fifth line

			G = new Graph(width * length); // Build a graph of excessive size to hold all nodes/rooms

			String line; // Declare variable for string
			int row = -1; // Declare and initialize integer for current row of labyrinth we are reading

			while ((line = input.readLine()) != null) { // Loop until we read a null string

				row++; // Increment current row

				for (int i = 0; i < line.length(); i++) { // Loop through current line
					if (i % 2 == 0 && row % 2 == 0) { // If we are reading a room
						if (line.charAt(i) == 'b') // If the character we are reading is b, we are reading the start
							entrance = (i / 2) + (row / 2) * width; // Set start to the corresponding node value
						else if (line.charAt(i) == 'x') // If its an x, its the exit
							exit = (i / 2) + (row / 2) * width; // Set Finish to the corresponding node value
					} else { // Otherwise we are reading an edge or blank space
						if (line.charAt(i) == 'h') // If its an h its a horizontal wall
							G.insertEdge(G.getNode((i / 2) + (row / 2) * width),
									G.getNode((i / 2) + (row / 2) * width + 1), "wall");
						else if (line.charAt(i) == 'H') // If its an H its a thick horizontal wall
							G.insertEdge(G.getNode((i / 2) + (row / 2) * width),
									G.getNode((i / 2) + (row / 2) * width + 1), "Twall");
						else if (line.charAt(i) == 'v') // If its a v its a vertical wall
							G.insertEdge(G.getNode((i / 2) + ((row - 1) / 2) * width),
									G.getNode((i / 2) + ((row + 1) / 2) * width), "wall");
						else if (line.charAt(i) == 'V') // If its a v its a thick vertical wall
							G.insertEdge(G.getNode((i / 2) + ((row - 1) / 2) * width),
									G.getNode((i / 2) + ((row + 1) / 2) * width), "Twall");
						else if (line.charAt(i) == 'm') // If its an m its a metal horizontal wall
							G.insertEdge(G.getNode((i / 2) + (row / 2) * width),
									G.getNode((i / 2) + (row / 2) * width + 1), "Mwall");
						else if (line.charAt(i) == 'M') // If its a M its a metal vertical wall
							G.insertEdge(G.getNode((i / 2) + ((row - 1) / 2) * width),
									G.getNode((i / 2) + ((row + 1) / 2) * width), "Mwall");
						else if (line.charAt(i) == '-') // If the character is a -, its a horizontal corridor
							G.insertEdge(G.getNode((i / 2) + (row / 2) * width),
									G.getNode((i / 2) + (row / 2) * width + 1), "corridor");
						else if (line.charAt(i) == '|') // If its an | its a vertical corridor
							G.insertEdge(G.getNode((i / 2) + ((row - 1) / 2) * width),
									G.getNode((i / 2) + ((row + 1) / 2) * width), "corridor");

					}
				}

			} // End of while line != null

			input.close(); // Close the input reader, as we have read the whole file

		} catch (Exception e) { // Catch any exceptions thrown while trying to create the labyrinth
			throw new LabyrinthException("Error creating labyrinth"); // Throw a new labyrinth exception
		}
	}

	// This method returns graph object of this Labyrinth
	public Graph getGraph() throws LabyrinthException {
		// If the graph does not exist, throw an exception
		if (G == null)
			throw new LabyrinthException("Graph is undefined");
		else // Otherwise return it
			return G;
	}

	// Method to solve and return an iterator of the labyrinth
	public Iterator solve() throws GraphException {

		if (G.getNode(entrance) == null || G.getNode(exit) == null)// Check if the entrance/exit is valid
			throw new GraphException("The entrance/exit node does not exist");
		else {
			S.push(G.getNode(entrance));
			return findPath(entrance, exit);
		}
	}

	private Iterator<Node> findPath(int en, int exit) {
		// variable declarations
		try {
			curNode = G.getNode(en);
			if (S.peek() != curNode)
				S.push(curNode);
			curNode.setMark(true);// set the current node as traversed
			// if its at destination, mark done and return iterator
			if (curNode.getName() == exit) {
				done = true;
			}
			// get incident edges
			nodeEdges = G.incidentEdges(curNode);
			// while there are edges
			while (nodeEdges.hasNext()) { // if exit has been found, return iterator.
				if (done == true) {
					return S.iterator();
				}
				tempEdge = nodeEdges.next();
				// check which node in the edge is the next node
				if (tempEdge.firstEndpoint() == curNode)
					nextNode = tempEdge.secondEndpoint();
				else
					nextNode = tempEdge.firstEndpoint();
				// if nextNode is not marked
				if (tempEdge.getLabel().equals("Been")) {
					if (nextNode.getMark() == true) {
						tempEdge.setLabel("back");
					} // if it's not marked,see the availability
					else {
						if (tempEdge.getType() == "corridor") {
							tempEdge.setLabel("Been");
							nextNode.setMark(true);// set it as traversed
							findPath(nextNode.getName(), exit);
						} else if (tempEdge.getType() == "wall" && k1 > 0) {// there's a brick bomb,bomb the wall
							tempEdge.setLabel("Been");
							tempEdge.setType("corridor");
							nextNode.setMark(true);// set it as traversed
							findPath(nextNode.getName(), exit);
						} else if (tempEdge.getType() == "Twall" && k1 >= 2) {// there's 2 brick bombs,bomb the thick
																				// wall
							tempEdge.setLabel("Been");
							tempEdge.setType("corridor");
							nextNode.setMark(true);// set it as traversed
							findPath(nextNode.getName(), exit);
						} else if (tempEdge.getType() == "Mwall" && k2 > 0) {// there's a acid bomb,bomb the metal wall
							tempEdge.setLabel("Been");
							tempEdge.setType("corridor");
							nextNode.setMark(true);// set it as traversed
							findPath(nextNode.getName(), exit);
						}
					}
				}
			} // if the above doesn't apply, need to backtrack
			if (!done) {
				curNode.setMark(false);
				S.pop();// invalid Node
				findPath(S.peek().getName(), exit);
			}
		} catch (GraphException e) {
			System.out.println(e);
		}
		if (S.isEmpty())
			return null;
		else
			return S.iterator();
	}
}
