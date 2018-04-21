import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

// undirected map
// shipment is directed
// always start in Sydney 
// always refuel at every place 

/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Graph 
{
	private LinkedList<Node> nodeList;	// list containing all nodes of this graph
	
	/**
	 * Constructor for class Graph
	 */
	public Graph () {
		nodeList = new LinkedList<Node>();
	}
	
	/**
	 * Adds node to graph
	 * @param newNode	node to be added to graph
	 */
	public void addNode(Node newNode) {
		if (!nodeList.contains(newNode)) {
			nodeList.add(newNode);
		}
	}
	
	/**
	 * Returns Node with specified name
	 * @param name	name of a node
	 * @return		if node exists returns the node
	 * 				else returns null
	 */
	public Node getNode(String name) {
		for (Node node : nodeList) {
			if (!node.getName().equals(name)) continue;
			return node;
		}
		return null;
	}
	
	/**
	 * Returns path from source to destination using A* search algorithm
	 * @param source		starting node of a path
	 * @param destination	ending node of a path
	 * @return				path from source to destination
	 */
	public List<Node> aStarSearch(Node source, Node destination) {
		SearchAlgorithm searchAlg = new Search();
		return searchAlg.getPath(source, destination);
	}
	
	/**
	 * Prints the given path
	 * @param path	list of nodes that forms a path
	 */
	public void showPath(List<Node> path) {
		System.out.print("\ncost = " + path.get(0).getFscore());
		Collections.reverse(path);
		//test printing path correct
		path.add(this.getNode("Shanghai"));
		path.add(this.getNode("Singapore"));
		path.add(this.getNode("Vancouver"));
		path.add(this.getNode("Sydney"));
		path.add(this.getNode("Vancouver"));
		
		for (int i = 1; i < path.size(); i++) {
			System.out.print("\nShip " + path.get(i-1).getName() + " to " + path.get(i).getName());
		}
	}
	
	// test
	public void showMap() {
		for (Node node : nodeList) {
			node.showEdge();
			System.out.println(node.getName() + " " + node.getRefuelTime());
		}
	}
	
	public void showShipments() {
		for (Node node : nodeList) {
			node.showNodeShipments();
		}
	}
}
