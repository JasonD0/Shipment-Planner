import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class AStarSearch<Node> {

	private Node startNode;
	private Node endNode;
	
	private PriorityQueue<Node> openSet = new PriorityQueue<Node>(); 
	private HashSet<Node> closedSet = new HashSet<Node>();
	private Map<Integer, Node> cameFrom = new HashMap<Integer, Node>();
	private Map<Node, Double> cost = new HashMap<Node, Double>();  // SELF NOTE: Double parameter can be null, double can't (similar with int)
	// pretend null is infinity
	
	
	
	public AStarSearch(Node startNode, Node endNode) {
		this.startNode = startNode;
		this.endNode = endNode;
	}
	
	public void getPath() {
		
	}
}
