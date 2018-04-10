import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class ShipmentPlanner {
	
    public static void main(String[] args) {
    	File input = new File(args[0]);
        Scanner sc = null;
        try {
            sc = new Scanner(input);  
            while (sc.hasNext()) {
            	String[] line = sc.nextLine().split("");
            	switch (line[0]) {
            		case "Refuelling": 
            			break;
            		case "Time":
            			break;
            		case "Shipment":
            			break;
            		default: 
            			break;
            	}
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            if (sc != null) sc.close();
        }
    }

}
