import java.util.List;

/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public interface SearchAlgorithm {
	public List<Node> getPath(Node source, Node destination);
}
