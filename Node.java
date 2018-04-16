import java.util.LinkedList;

public class Node<E> 
{
	private int nodeId;
	private LinkedList<Edge<E>> edgesList;
	private int refuelTime; 
	private String name;
 	
	public Node(int id, int refuelTime, String name) {
		this.nodeId = id;
		this.refuelTime = refuelTime;
		this.name = name;
		this.edgesList = new LinkedList<Edge<E>>();
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
	
	public void addEdge(Node<E> destination, int cost) {
		Edge<E> newEdge = new Edge<E>(destination, cost);
		if (!edgesList.contains(newEdge)) {
			edgesList.add(newEdge);
		}
	}
	
	
	// test
	public void showEdge() {
		System.out.print(this.name + " -> {");
		for (Edge<E> edge : edgesList) {
			System.out.print(edge.getNodeTo().getName() + " ");
		}
		System.out.println("}");
	}
	
}
