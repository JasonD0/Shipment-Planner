import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShipmentsList<E> 
{
	private HashMap<Node<E>, ArrayList<Node<E>>> shipmentsList;
	
	public ShipmentsList() {
		this.shipmentsList = new HashMap<Node<E>, ArrayList<Node<E>>>();
	}
	
	public void insertShipment(Node<E> source, Node<E> destination) {
		if (!shipmentsList.containsKey(source)) {
			ArrayList<Node<E>> shipmentsTo = new ArrayList<Node<E>>();
			shipmentsTo.add(destination);
			shipmentsList.put(source, shipmentsTo);
		} else {
			ArrayList<Node<E>> shipmentsTo = this.shipmentsList.get(source);
			shipmentsTo.add(destination);
		}
	}
	
	public HashMap<Node<E>, ArrayList<Node<E>>> getShipmentsList() {
		return this.shipmentsList;
	}
	
	
	// maybe need functions below
	public ArrayList<Node<E>> getShipmentsTo(Node<E> source) {
		return this.shipmentsList.get(source);
	}
	
	public boolean checkShipmentTo(Node<E> source, Node<E> destination) {
		return shipmentsList.get(source).contains(destination);
	}
	
	
	// test
	public void showShipments() {
		for (Map.Entry<Node<E>, ArrayList<Node<E>>> entry : shipmentsList.entrySet()) {
			System.out.print(entry.getKey().getName() + " -> {");
			ArrayList<Node<E>> shipmentsTo = entry.getValue();
			for (Node<E> node : shipmentsTo) {
				System.out.print(node.getName() + " ");
			}
			System.out.println("}");
		}
	}
}
