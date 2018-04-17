import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class ShipmentPlanner 
{
	
    public static <E> void main(String[] args) {
    	Graph<E> map = new Graph<E>();
    	File input = new File(args[0]);
        Scanner sc = null;
        try {
        	int nodeId = 0;
            sc = new Scanner(input);  
            while (sc.hasNext()) {
            	String[] line = sc.nextLine().split(" ");
            	switch (line[0]) {
            		case "Refuelling": 
            			Node<E> newNode = new Node<E>(nodeId++, Integer.parseInt(line[1]), line[2]);
            			map.addNode(newNode);
            			break;
            		case "Time":
            			Node<E> source = map.getNode(line[2]);
            			source.addEdge(map.getNode(line[3]), Integer.parseInt(line[1]));
            			source = map.getNode(line[3]);
            			source.addEdge(map.getNode(line[2]), Integer.parseInt(line[1]));
            			break;
            		case "Shipment":
            			Node<E> shipmentFrom = map.getNode(line[1]);
            			shipmentFrom.addShipment(map.getNode(line[2]));
            			break;
            		default: 
            			break;
            	}
            }
            // test
            map.showMap();
            map.showShipments();
//            map.getPath(map.getNode("Sydney"), map.getNode("Manila"));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (sc != null) sc.close();
        }
    }

}
