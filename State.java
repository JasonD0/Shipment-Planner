import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class State implements Comparable<State>
{
	private List<Node> path;
	private Map<Node, List<Node>> shipmentsList;	
	private int fScore;
	
	public State(int fScore) {
		this.path = new LinkedList<Node>();
		this.fScore = fScore;
	}
	
	public void initShipmentsDone(Map<Node, List<Node>> shipments) {
		this.shipmentsList = shipments;
		/*for (Map.Entry<Node, List<Node>> idk : shipmentsList.entrySet()) {
			for (Node node : idk.getValue()) {
				System.out.println( idk.getKey().getName()+ " : " +node.getName());
			}
		}*/
	}
	
	public boolean checkGoalState() {
		for (Map.Entry<Node, List<Node>> idk : shipmentsList.entrySet()) {
			if (!idk.getValue().isEmpty()) return false;
		}
		return true;
	}
	
	public List<Node> getPath() {
		return this.path;
	}
	
	public Node getCurrentNode() {
		return this.path.get(path.size() - 1);
	}
	
	public int getFscore() {
		return this.fScore;
	}
	
	public void setFscore(int fScore) {
		this.fScore = fScore;
	}
	
	public void addNode(Node node) {
		// if previous node(shipmentFrom) value contains node inserted(shipmentTo)   -> shipment done
		if (!path.isEmpty()) {
			Node shipmentFrom = this.path.get(path.size() - 1);
			if (!shipmentsList.get(shipmentFrom).isEmpty() && shipmentsList.get(shipmentFrom).contains(node)) {
				if (shipmentsList.get(shipmentFrom).size() == 1) shipmentsList.remove(shipmentFrom);
				else shipmentsList.get(shipmentFrom).remove(node);
			}
		}
		this.path.add(node);
	}
	
	public void addPath(List<Node> path) {
		for (Node node : path) {
			this.path.add(node);
		}
	} 
	
	@Override
	public int compareTo(State i) {
		if (this.fScore > i.fScore) return 1; 
		if (this.fScore < i.fScore) return -1; 
		return 0;
	}
	
}
