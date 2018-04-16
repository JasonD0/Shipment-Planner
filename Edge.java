
public class Edge<E>
{
	private Node<E> nodeTo;
	private int cost;
	
	public Edge(Node<E> nodeTo, int cost) {
		this.nodeTo = nodeTo;
		this.cost = cost;
	}
	
	public Node<E> getNodeTo() {
		return this.nodeTo;
	}
	
	public int getCost() {
		return this.cost;
	}
}
