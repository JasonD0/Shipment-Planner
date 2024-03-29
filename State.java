import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Representation of a state of the graph produced from the A star search algorithm
 * @invariant shipments are distinct and not null
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class State implements Comparable<State>
{
	private List<Node> path;						// list of nodes forming the current path
	private int gScore;								// cost of the current path
	private int fScore;								// cost of the current path + estimated cost to the goal state
	private Map<Node, List<Node>> shipmentsMade;	// map between shipments source and destination, representing shipments completed

	/**
	 * Constructor for State class
	 * @param fScore    distance from start node to goal state
	 */
	public State(int gScore, int fScore, List<Node> path, Map<Node, List<Node>> shipments) {
		this.path = new LinkedList<>(path);
		this.gScore = gScore;
		this.fScore = fScore;
		this.shipmentsMade = new HashMap<Node, List<Node>>(shipments);
	}

	/**
	 * Checks if the state given satisfies the goal state of the algorithm
	 * @param shipments       map representing the shipments completed
	 * @return				  true if goal state satisfied
	 * 						  false otherwise
	 * @postcondition 		  checks if the goal state has been satisfied
	 */
	public boolean checkGoalState(Map<Node, List<Node>> shipments) {
		for (Map.Entry<Node, List<Node>> shipmentsRequired : shipments.entrySet()) {
			Node shipmentFrom = shipmentsRequired.getKey();
			for (Node shipmentTo : shipmentsRequired.getValue()) {
				if (!checkShipmentsMade(shipmentFrom, shipmentTo)) return false;
			}
		}
		return true;
	}

	/**
	 * Returns smallest shipment destination for the shipment source that havent formed the shipment
	 * @precondition          shipmentFrom is a shipment source
	 * @param shipmentList    list of shipments required
	 * @param shipmentFrom	  source node of a shipment
	 * @return				  node
	 * @postcondition         returns smallest shipment destination for given shipment source
	 */
	public Node getSmallestShipmentTo(Map<Node, List<Node>> shipmentList, Node shipmentFrom) {
		Node smallest = null;
		int smallestCost = Integer.MAX_VALUE;
		for (Node shipmentTo : shipmentList.get(shipmentFrom)) {
			// if the current shipmentTo is shipment destination continue
			if (checkShipmentsMade(shipmentFrom, shipmentTo)) continue;
			int currentCost = shipmentTo.getEdgeCost(shipmentFrom) + shipmentTo.getRefuelTime();
			if (smallestCost > currentCost) {
				smallestCost = currentCost;
				smallest = shipmentTo;
			}
		}
		return smallest;
	}

	/**
	 * Return lowest edge cost for given node
	 * @param source     source node of an edge
	 * @return			 edge cost
	 * @postcondition    returns lowest edge cost for source
	 */
	public int getLowestEdgeCost(Node source) {
		int smallestCost = Integer.MAX_VALUE;
		for (Edge destination : source.getEdges()) {
			int currentCost = destination.getCost() + destination.getNode().getRefuelTime();
			if (smallestCost > currentCost) {
				smallestCost = currentCost;
			}
		}
		return smallestCost;
	}

	/**
	 * Adds the shipment completed to shipmentsMade
	 * @param shipmentFrom    source node of the shipment
	 * @param shipmentTo	  destination node of the shipment
	 * @postcondition         adds the completed shipment to shipmentsMade hashmap
	 */
	public void addNewShipment(Node shipmentFrom, Node shipmentTo) {
		// add new shipment source
		if (!shipmentsMade.containsKey(shipmentFrom)) {
			List<Node> shipmentsTo = new ArrayList<Node>();
			shipmentsTo.add(shipmentTo);
			shipmentsMade.put(shipmentFrom, shipmentsTo);
		// add shipment destination for existing shipment source
		} else if (!shipmentsMade.get(shipmentFrom).contains(shipmentTo)) {
			shipmentsMade.get(shipmentFrom).add(shipmentTo);
		}
	}

	/**
	 * Returns list of shipments made
	 * @return    		 shipments made
	 * @postcondition    returns hashmap of shipments made
	 */
	public Map<Node, List<Node>> getShipmentsMade() {
		return this.shipmentsMade;
	}

	/**
	 * Returns a copy of shipments made
	 * @return           shipments made
	 * @postcondition    return copy of the hashmap of shipments made
	 */
	public Map<Node, List<Node>> copyShipmentsMade() {
		// only need return copy of the list -> only one changing -> not chanigng shipment from -> changing the new map
		Map<Node, List<Node>> copy = new HashMap<Node, List<Node>>();
		for (Map.Entry<Node, List<Node>> original : this.shipmentsMade.entrySet()) {
			copy.put(original.getKey(), new LinkedList<Node>(original.getValue()));
		}
		return copy;
	}

	/**
	 * Checks if shipment has been made
	 * @param shipmentFrom    node to check shipment source
	 * @param shipmentTo	  node to check shipment destination
	 * @return				  true if shipment has been made
	 * 						  false otherwise
	 * @postcondition 		  checks whether a shipment has been made
	 */
	public boolean checkShipmentsMade(Node shipmentFrom, Node shipmentTo) {
		if (!this.shipmentsMade.containsKey(shipmentFrom)) return false;
		if (!this.shipmentsMade.get(shipmentFrom).contains(shipmentTo)) return false;
		return true;
	}

	/**
	 * Checks if node is a shipment source
	 * @param shipmentFrom    node to check shipment source
	 * @return				  true if node is shipment source
	 * 						  false otherwise
	 * @postcondition         checks whether shipmentFrom is a shipment source
	 */
	public boolean isShipmentSource(Node shipmentFrom) {
		if (this.shipmentsMade.get(shipmentFrom) == null || this.shipmentsMade.get(shipmentFrom).size() == 0) return false;
		return true;
	}

	/**
	 * Returns shipment destination for a shipment source
	 * @precondition          shipmentFrom is a shipment source
	 * @param shipmentFrom    source node of shipment
	 * @return				  list of shipment destinations
	 * @postcondition         returns list of shipment destinations for shipment source
	 */
	public List<Node> getShipmentsTo(Node shipmentFrom) {
		return this.shipmentsMade.get(shipmentFrom);
	}

	/**
	 * Returns copy of the path of visited nodes
	 * @return    		 path
	 * @postcondition    returns copy of path taken
	 */
	public List<Node> getPath() {
		List<Node> copy = new LinkedList<Node>();
		for (Node node : this.path) {
			copy.add(node);
		}
		return copy;
	}

	/**
	 * Returns last visited node
	 * @return    	 	 last element of the path
	 * @postcondition    returns the last node visited
	 */
	public Node currentNode() {
		return this.path.get(path.size() - 1);
	}

	/**
	 * Returns cost of the path
	 * @return    		 gScore
	 * @postcondition    returns the gScore
	 */
	public int getGscore() {
		return this.gScore;
	}

	/**
	 * Sets value of fScore
	 * @param fScore     cost of the path + heuristic
	 * @postcondition    sets the fscore to the given value
	 */
	public void setFscore(int fScore) {
		this.fScore = fScore;
	}

	/**
	 * Adds node to path
	 * @param node       node to be added to path
	 * @postcondition    adds the given node to the path
	 */
	public void addNode(Node node) {
		this.path.add(node);
	}

	/**
	 * Comparator orders States by their fScores
	 * lowest fScore has high priority
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(State i) {
		if (this.fScore > i.fScore) return 1; 
		if (this.fScore < i.fScore) return -1; 
		return 0;
	}
	
}
