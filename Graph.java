import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

// undirected map
// shipment is directed
// always start in Sydney 
// always refuel at every place 

/**
 * Representation of all links between all ports in a map
 * @invariant follows the triangle inequality
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
	 * @precondition 	 node to be added is new to the graph
	 * @param newNode    node to be added to graph
	 * @postcondition 	 add node to the graph
	 */
	public void addNode(Node newNode) {
		nodeList.add(newNode);
	}
	
	/**
	 * Returns Node with specified name
	 * @param name    	 name of a node
	 * @return		  	 if node exists returns the node
	 * 				  	 else returns null
	 * @postcondition    returns the node with the given name
	 */
	public Node getNode(String name) {
		for (Node node : nodeList) {
			if (!node.getName().equals(name)) continue;
			return node;
		}
		return null;
	}

	/**
	 * Returns all nodes in the graph and the respective shipments
	 * @return    		 map between each node and their list of shipments
	 * @postcondition    returns a hashmap of shipments required
	 */
	public Map<Node, List<Node>> getShipments() {
		Map<Node, List<Node>> shipments = new HashMap<Node, List<Node>>();
		// maps shipment source node with shipment destination node
		for (Node shipmentFrom : nodeList) {
			shipments.put(shipmentFrom, shipmentFrom.getShipments());
		}
		return shipments;
	}
	
	/**
	 * Returns path from source to destination using A* search algorithm
	 * @param source     starting node of a path
	 * @return			 path from source to destination
	 * @postcondition    returns the optimum path from source node to the goal state
	 */
	// remove destination
	public List<Node> aStarSearch(Node source) {
		Search searchAlg = new Search();
		return searchAlg.getPath(this, source);
	}
	
	/**
	 * Prints the given path
	 * @precondition     path != null
	 * @param path       list of nodes that forms a path
	 * @postcondition    prints the path
	 */
	public void showPath(List<Node> path) {
		//System.out.print("\ncost = " + path.get(0).getFscore());
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
