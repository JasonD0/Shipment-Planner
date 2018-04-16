
public class Shipments<E> 
{
	private Node<E> shipmentFrom;
	private Node<E> shipmentTo;
	
	public Shipments(Node<E> source, Node<E> destination) {
		this.shipmentFrom = source;
		this.shipmentTo = destination;
	}
	
	public Node<E> getShipmentFrom() {
		return this.shipmentFrom;
	}
	
	public Node<E> getShipmentTo() {
		return this.shipmentTo;
	}
}
