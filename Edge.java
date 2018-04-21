
/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Edge
{
	private Node node;	// destination node of this edge
	private int cost;
	
	/**
	 * Constructor for class Edge
	 * @param nodeTo	the destination node of this edge 
	 * @param cost		the cost of going through this edge
	 */
	public Edge(Node nodeTo, int cost) {
		this.node = nodeTo;
		this.cost = cost;
	}
	
	/**
	 * Returns the destination node of this edge
	 * @return destination node
	 */
	public Node getNode() {
		return this.node;
	}
	
	/**
	 * Returns the cost of going through this edge
	 * @return cost
	 */
	public int getCost() {
		return this.cost;
	}
}
