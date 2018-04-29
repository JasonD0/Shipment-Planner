
/**
 * The representation of the link between two ports (nodes)
 * @invariant edge cost >= 0
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
	 * @param nodeTo    the destination node of this edge
	 * @param cost		the cost of going through this edge
	 */
	public Edge(Node nodeTo, int cost) {
		this.node = nodeTo;
		this.cost = cost;
	}
	
	/**
	 * Returns the destination node of this edge
	 * @return    		 destination node
	 * @postcondition    returns the destination node of this edge
	 */
	public Node getNode() {
		return this.node;
	}
	
	/**
	 * Returns the cost of going through this edge
	 * @return           cost
	 * @postcondition    returns the cost of going through this edge
	 */
	public int getCost() {
		return this.cost;
	}
}
