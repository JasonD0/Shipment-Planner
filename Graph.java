import java.util.LinkedList;
import java.util.List;

// undirected map
// shipment is directed
// always start in Sydney 
// always refuel at every place 

public class Graph<E> 
{
	private LinkedList<Node<E>> nodeList;
	
	public Graph () {
		nodeList = new LinkedList<Node<E>>();
	}
	
	public List<Node<E>> getPath(Node<E> source, Node<E> destination) {
		AStarSearch<E> searchAlg = new AStarSearch<E>();
		return searchAlg.getPath(source, destination, nodeList.size());
	}
	
	public void addNode(Node<E> newNode) {
		if (!nodeList.contains(newNode)) {
			nodeList.add(newNode);
		}
	}
	
	public Node<E> getNode(String name) {
		for (Node<E> node : nodeList) {
			if (!node.getName().equals(name)) continue;
			return node;
		}
		return null;
	}
	
	// test
	public void showMap() {
		for (Node<E> node : nodeList) {
			node.showEdge();
			System.out.println(node.getName() + " " + node.getRefuelTime());
		}
	}
	
	public void showShipments() {
		for (Node<E> node : nodeList) {
			node.showNodeShipments();
		}
	}
}
