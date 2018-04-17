import java.util.LinkedList;

public class Node 
{
	private LinkedList<Edge> edgesList;
	private LinkedList<Node> shipmentsList;
	private int refuelTime; 
	private String name;
	private int fScore;
	private int gScore;
	private int heuristic;
 	
	public Node(int id, int refuelTime, String name, int gScore, int fScore) {
		this.refuelTime = refuelTime;
		this.name = name;
		this.gScore = gScore;
		this.fScore = fScore;
		this.edgesList = new LinkedList<Edge>();
		this.shipmentsList = new LinkedList<Node>();
	}
	
	public int getRefuelTime() {
		return this.refuelTime;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getFscore() {
		return this.fScore;
	}
	
	public int getGscore() {
		return this.gScore;
	}
	
	public void setFscore() {
		this.fScore = this.gScore + this.heuristic;
	}
	
	public void setGscore(int gscore) {
		this.gScore = gscore;
	}
	
	public void setHeuristic() {
		this.heuristic = 0;
	}
	
	public LinkedList<Edge> getEdges() {
		return this.edgesList;
	}
	
	public void addEdge(Node destination, int cost) {
		Edge newEdge = new Edge(destination, cost);
		if (!edgesList.contains(newEdge)) {
			edgesList.add(newEdge);
		}
	}
	
	public int getEdgeCost(Node destination) {
		for (Edge neighbour : edgesList) {
			if (destination.equals(neighbour.getNodeTo())) {
				return neighbour.getCost();
			}
		}
		return Integer.MAX_VALUE /*infinity*/;
	}
	
	public void addShipment(Node destination) {
		if (!shipmentsList.contains(destination)) {    // for all others like this (checking) -> remove -> check in main(?) -> precondition
			shipmentsList.add(destination);
		} 
	}
	
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
			System.out.print(edge.getNodeTo().getName() + " ");
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
