import java.util.LinkedList;
import java.util.List;

/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Node 
{
	private LinkedList<Edge> edgesList;
	private LinkedList<Node> shipmentsList;
	private int refuelTime; 
	private String name;
 	
	/**
	 * Constructor for class Node
	 * @param refuelTime	refuelling time at this node
	 * @param name			name of this node
	 * @param gScore		gScore of this node
	 * @param fScore		fScore of this node
	 */
	public Node(int refuelTime, String name, int gScore, int fScore) {
		this.refuelTime = refuelTime;
		this.name = name;
		this.edgesList = new LinkedList<Edge>();
		this.shipmentsList = new LinkedList<Node>();
	}
	
	/**
	 * Returns refuel time at this node
	 * @return	refuel time
	 */
	public int getRefuelTime() {
		return this.refuelTime;
	}
	
	/**
	 * Returns name of this node
	 * @return	name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns list of edges for this node
	 * @return	list of edges
	 */
	public LinkedList<Edge> getEdges() {
		return this.edgesList;
	}
	
	/**
	 * Adds edge from this node to the destination node
	 * @param destination	the end node to the edge
	 * @param cost			the cost of going from this node to destination node
	 */
	public void addEdge(Node destination, int cost) {
		Edge newEdge = new Edge(destination, cost);
		if (!edgesList.contains(newEdge)) {
			edgesList.add(newEdge);
		}
	}
	
	/**
	 * Returns the cost of passing the edge 
	 * @param destination	the end node of the edge
	 * @return 				if destination forms an edge with this node returns the cost of the edge, 
	 * 						else the maximum integer value 
	 */
	public int getEdgeCost(Node destination) {
		for (Edge neighbour : edgesList) {
			if (destination.equals(neighbour.getNode())) {
				return neighbour.getCost();
			}
		}
		return Integer.MAX_VALUE /*infinity*/;
	}
	
	/**
	 * Adds required shipment destination to this node
	 * @param destination	node in which this node must go to
	 */
	public void addShipment(Node destination) {
		if (!shipmentsList.contains(destination)) {    // for all others like this (checking) -> remove -> check in main(?) -> precondition
			shipmentsList.add(destination);
		} 
	}
	
	public List<Node> getShipments() {
		return this.shipmentsList;
	}
	
	/**
	 * Checks if destination is a node this node must go to
	 * @param destination
	 * @return	if this node's shipment list contains destination return true
	 * 			else return false
	 */
	public boolean checkShipmentTo(Node destination) {
		return shipmentsList.contains(destination);
	}
	
	
	/*
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null) return false;
		if (this.getClass() != o.getClass()) return false;
		
		Node<?> node = (Node<?>) o;
		return (this.nodeId == node.nodeId && 
				this.refuelTime == node.refuelTime && 
				this.name.equals(node.name)); 
	}*/
	
	
	// test
	public void showEdge() {
		System.out.print(this.name + " -> {");
		for (Edge edge : edgesList) {
			System.out.print(edge.getNode().getName() + " ");
		}
		System.out.println("}");
	}
	
	public void showNodeShipments() {
		System.out.print(this.name + " -> {");
		for (Node node : shipmentsList) {
			System.out.print(node.getName() + " ");
		}
		System.out.println("}");
	}
}
