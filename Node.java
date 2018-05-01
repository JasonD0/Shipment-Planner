import java.util.LinkedList;
import java.util.List;

/**
 * Representation of a particular port on the graph
 * @invariant name of node is one word, shipments are distinct and not null,
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Node 
{
	private List<Edge> edgesList;
	private List<Node> shipmentsList;
	private int refuelTime; 
	private String name;
 	
	/**
	 * Constructor for class Node
	 * @param refuelTime    refuelling time at this node
	 * @param name			name of this node
	 */
	public Node(int refuelTime, String name) {
		this.refuelTime = refuelTime;
		this.name = name;
		this.edgesList = new LinkedList<Edge>();
		this.shipmentsList = new LinkedList<Node>();
	}
	
	/**
	 * Returns refuel time at this node
	 * @return    		 refuel time
	 * @postcondition    returns the refuelling time of this node
	 */
	public int getRefuelTime() {
		return this.refuelTime;
	}
	
	/**
	 * Returns name of this node
	 * @return    		 name
	 * @postcondition    returns the name of this node
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns list of edges for this node
	 * @return    		 list of edges
	 * @postcondition    returns the list of edges of this map
	 */
	public List<Edge> getEdges() {
		return this.edgesList;
	}
	
	/**
	 * Adds edge from this node to the destination node
	 * @precondition 		 destination node for this node doesnt already exist
	 * @param destination    the end node to the edge
	 * @param cost			 the cost of going from this node to destination node
	 * @postcondition		 adds an edge for this node
	 */
	public void addEdge(Node destination, int cost) {
		Edge newEdge = new Edge(destination, cost);
		edgesList.add(newEdge);
	}
	
	/**
	 * Returns the cost of passing the edge
	 * @param destination    the end node of the edge
	 * @return 				 the cost of the edge if destination forms an edge with this node
	 * 						 else the maximum integer value
	 * @postcondition        returns the cost of getting to the given destination node
	 */
	public int getEdgeCost(Node destination) {
		for (Edge neighbour : edgesList) {
			if (destination.equals(neighbour.getNode())) {
				return neighbour.getCost();
			}
		}
		return Integer.MAX_VALUE;
	}
	
	/**
	 * Adds required shipment destination to this node
	 * @precondition         destination node for the shipment is unique
	 * @param destination    node in which this node must go to
	 * @postcondition        add shipment destination for this node
	 */
	public void addShipment(Node destination) {
		shipmentsList.add(destination);
	}

	/**
	 * Returns list of shipments for this node
	 * @return    		 shipmentsList
	 * @postcondition    returns list of shipments required for this node
	 */
	public List<Node> getShipments() {
		return this.shipmentsList;
	}
	
}
