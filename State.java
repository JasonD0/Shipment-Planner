import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class State implements Comparable<State>
{
	private List<Node> path;						// list of nodes forming the path
	private Map<Node, List<Node>> shipmentsToDo;	// map between remaining shipments start node and list of it's shipments end node
	private int fScore;

	/**
	 * Constructor for State class
	 * @param fScore	distance from start node to goal state
	 */
	public State(int fScore) {
		this.path = new LinkedList<Node>();
		this.fScore = fScore;
	}

	/**
	 * Initialise shipmentLists to all shipments required
	 * @param shipments		map between all shipment start nodes and list of shipment end nodes
	 */
	public void initShipmentsToDoList(Map<Node, List<Node>> shipments) {
		this.shipmentsToDo = shipments;
		/*for (Map.Entry<Node, List<Node>> idk : shipmentsList.entrySet()) {
			for (Node node : idk.getValue()) {
				System.out.println( idk.getKey().getName()+ " : " +node.getName());
			}
		}*/
	}

	/**
	 * Checks if all shipments completed
	 * @return	if shipmentsToDo is empty return true
	 * 			else return false
	 */
	public boolean checkGoalState() {
		for (Map.Entry<Node, List<Node>> idk : shipmentsToDo.entrySet()) {
			if (!idk.getValue().isEmpty()) return false;
		}
		return true;
	}

	/**
	 * Returns path of visited nodes
	 * @return	path
	 */
	public List<Node> getPath() {
		return this.path;
	}

	/**
	 * Returns last visited node
	 * @return	last element of the path
	 */
	public Node getCurrentNode() {
		return this.path.get(path.size() - 1);
	}

	/**
	 * Returns total length from the start node to last node in path
	 * @return	fScore
	 */
	public int getFscore() {
		return this.fScore;
	}

	/**
	 * Sets value of fScore
	 * @param fScore	total length from the start node to last in path
	 */
	public void setFscore(int fScore) {
		this.fScore = fScore;
	}

	/**
	 * Adds node to path
	 * @param node	node to be added to path
	 */
	public void addNode(Node node) {
		// if previous node(shipmentFrom) value contains node inserted(shipmentTo)   -> shipment done
		if (!path.isEmpty()) {
			Node shipmentFrom = this.path.get(path.size() - 1);
			if (!shipmentsToDo.get(shipmentFrom).isEmpty() && shipmentsToDo.get(shipmentFrom).contains(node)) {
				if (shipmentsToDo.get(shipmentFrom).size() == 1) shipmentsToDo.remove(shipmentFrom);
				else shipmentsToDo.get(shipmentFrom).remove(node);
			}
		}
		this.path.add(node);
	}

	/**
	 * Combines this state's path with another path
	 * @param path	path to be added to this state's path
	 */
	public void addPath(List<Node> path) {
		for (Node node : path) {
			this.path.add(node);
		}
	}

	/**
	 * Comparator orders States by their fScores
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(State i) {
		if (this.fScore > i.fScore) return 1; 
		if (this.fScore < i.fScore) return -1; 
		return 0;
	}
	
}
