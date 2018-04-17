import java.util.LinkedList;
import java.util.List;

// undirected map
// shipment is directed
// always start in Sydney 
// always refuel at every place 

public class Graph 
{
	private LinkedList<Node> nodeList;
	
	public Graph () {
		nodeList = new LinkedList<Node>();
	}
	public void addNode(Node newNode) {
		if (!nodeList.contains(newNode)) {
			nodeList.add(newNode);
		}
	}
	
	public Node getNode(String name) {
		for (Node node : nodeList) {
			if (!node.getName().equals(name)) continue;
			return node;
		}
		return null;
	}
	
	public List<Node> aStarSearch(Node source, Node destination) {
		SearchAlgorithm searchAlg = new Search();
		return searchAlg.getPath(source, destination);
	}
	
	// test
	public void showMap() {
		for (Node node : nodeList) {
			node.showEdge();
			System.out.println(node.getName() + " " + node.getRefuelTime());
		}
	}
	
	public void showShipments() {
		for (Node node : nodeList) {
			node.showNodeShipments();
		}
	}
}
