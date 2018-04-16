import java.util.LinkedList;
// directed map
public class Graph<E> 
{
	private LinkedList<Node<E>> nodeList;
	
	public Graph () {
		nodeList = new LinkedList<Node<E>>();
	}
	
	public LinkedList<Node<E>> getPath(Node<E> source, Node<E> destination) {
		LinkedList<Node<E>> path = new LinkedList<Node<E>>();
		return path; 
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
}
