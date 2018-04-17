import java.util.LinkedList;

public class Node<E> 
{
	private int nodeId; // not needed?
	private LinkedList<Edge<E>> edgesList;
	private LinkedList<Node<E>> shipmentsList;
	private int refuelTime; 
	private String name;
 	
	public Node(int id, int refuelTime, String name) {
		this.nodeId = id;
		this.refuelTime = refuelTime;
		this.name = name;
		this.edgesList = new LinkedList<Edge<E>>();
		this.shipmentsList = new LinkedList<Node<E>>();
	}
	
	public int getNodeId() {
		return this.nodeId;
	}
	
	public int getRefuelTime() {
		return this.refuelTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public LinkedList<Edge<E>> getEdges() {
		return this.edgesList;
	}
	
	public void addEdge(Node<E> destination, int cost) {
		Edge<E> newEdge = new Edge<E>(destination, cost);
		if (!edgesList.contains(newEdge)) {
			edgesList.add(newEdge);
		}
	}
	
	public int getEdgeCost(Node<E> destination) {
		for (Edge<E> neighbour : edgesList) {
			if (destination.equals(neighbour.getNodeTo())) {
				return neighbour.getCost();
			}
		}
		return 0 /*infinity*/;
	}
	
	public void addShipment(Node<E> destination) {
		if (!shipmentsList.contains(destination)) {    // for all others like this (checking) -> remove -> check in main(?) -> precondition
			shipmentsList.add(destination);
		} 
	}
	
	public boolean checkShipmentTo(Node<E> destination) {
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
		for (Edge<E> edge : edgesList) {
			System.out.print(edge.getNodeTo().getName() + " ");
		}
		System.out.println("}");
	}
	
	public void showNodeShipments() {
		System.out.print(this.name + " -> {");
		for (Node<E> node : shipmentsList) {
			System.out.print(node.getName() + " ");
		}
		System.out.println("}");
	}
}
