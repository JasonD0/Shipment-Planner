
/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Edge
{
	private Node nodeTo;
	private int cost;
	
	public Edge(Node nodeTo, int cost) {
		this.nodeTo = nodeTo;
		this.cost = cost;
	}
	
	public Node getNodeTo() {
		return this.nodeTo;
	}
	
	public int getCost() {
		return this.cost;
	}
}
