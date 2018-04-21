import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipmentsList 
{
	private HashMap<Node, ArrayList<Node>> shipmentsList;
	
	public ShipmentsList() {
		this.shipmentsList = new HashMap<Node, ArrayList<Node>>();
	}
	
	public void insertShipment(Node source, Node destination) {
		if (!shipmentsList.containsKey(source)) {
			ArrayList<Node> shipmentsTo = new ArrayList<Node>();
			shipmentsTo.add(destination);
			shipmentsList.put(source, shipmentsTo);
		} else {
			ArrayList<Node> shipmentsTo = this.shipmentsList.get(source);
			shipmentsTo.add(destination);
		}
	}

	public HashMap<Node, ArrayList<Node>> getShipmentsList() {
		return this.shipmentsList;
	}
	
	
	// maybe need functions below
	public ArrayList<Node> getShipmentsTo(Node source) {
		return this.shipmentsList.get(source);
	}
	
	public boolean checkShipmentTo(Node source, Node destination) {
		return shipmentsList.get(source).contains(destination);
	}

	
	// test
	public void showShipments() {
		for (Map.Entry<Node, ArrayList<Node>> entry : shipmentsList.entrySet()) {
		System.out.print(entry.getKey().getName() + " -> {");
			ArrayList<Node> shipmentsTo = entry.getValue();
			for (Node node : shipmentsTo) {
				System.out.print(node.getName() + " ");
		}
			System.out.println("}");
		}
	}
}