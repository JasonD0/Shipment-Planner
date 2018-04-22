import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Search implements SearchAlgorithm 
{
	// instead of destination -> list of shipments -> when empty then reconstruct path
	/**
	 * Implements the A star algorithm and returns the path from source to destination
	 * @param map			graph containing all nodes
	 * @param source		starting node 
	 * @param destination	ending node 
	 * @return				list of nodes forming a path from source to destination
	 */
	public List<Node> getPath(Graph map, Node source, Node destination) {

		// test checkGoalState
		/*
		State state = new State(0);
		Map<Node, List<Node>> shipments = map.getShipments();
		state.initShipmentsToDoList(shipments);
		state.addNode(map.getNode("Sydney"));
		state.addNode(map.getNode("Vancouver"));
		state.addNode(map.getNode("Sydney"));
		state.addNode(map.getNode("Manila"));
		state.addNode(map.getNode("Singapore"));
		state.addNode(map.getNode("Vancouver"));
		state.addNode(map.getNode("Shanghai"));
		state.addNode(map.getNode("Singapore"));
		if (state.checkGoalState()) System.out.println("SUCCESS");
		else System.out.println("FAIL");
		*/


		// CURRENTLY ONLY USES GSCORE IE NO HEURISTICS HENCE NO FSCORE
		// DONT KNOW IF PATH OPTIMAL NEED PRINT COST TO TEST AND SEE, NEED VARIABLES FOR N NODE EXPANDED

		PriorityQueue<State> mapStates = new PriorityQueue<State>();			// Queue containing a path searched	
		List<State> visited = new ArrayList<State>();							// List of paths visited
		Map<Node, List<Node>> shipments = map.getShipments();

		// why does creating new Hashmap for shipments in each init -> still change all AND goes infinite

		//List<Node> path = new LinkedList<Node>();
		State initialState = new State(0);		// initially fScore is 0
		initialState.addNode(source);					// add source to state path
		initialState.initShipmentsToDoList(shipments);	// initialise shipment list for state
		mapStates.add(initialState);

		// get state where path lowest and contains shipment
		while (!mapStates.isEmpty()) {																	// while OPEN is not empty
			State currentState = mapStates.poll();														// remove <n, p> from head of queue (minimal f(n))
			currentState.showPath();
			visited.add(currentState);																	//  add <n, p> to CLOSED
			if (currentState.checkGoalState()) {
				currentState.showPath();
				return currentState.getPath();							// if n is a goal state return success path p
			}
			// problem : shipmentToDoList -> removes from all not each one
			for (Edge e : currentState.getCurrentNode().getEdges()) {									// for each edge e from n to n� (with cost c, so g(n�) = g(n) + c)
				State newState = new State(currentState.getFscore() + e.getCost());
				newState.initShipmentsToDoList(shipments);
				newState.setPath(currentState.getPath());
				newState.addNode(e.getNode());
				mapStates.add(newState);				// new state for each edge possibility
				// change design so dont give state shipment
				// check if shipment here
				// instead of orig if else -> do if edge is shipmentTo -> go to lowest
												// else prioritise lowest shipment from
			}
		}
		// when testing, comment out below and see if get the optimal path then test with below to see if get less nodes expanded
		// complete graph -> so cant remove/replace/add based on node -> check path (?)
		/*
		// n' is edge dest, n is current node,
		// so <n', p'> a path with n',  ie a path already gotten to this node **** #solved
		// pq should only have one path with n' -> the lowest currently    in lec version  
		// similarly with visited

		return failure 
		*/
				
				
		return null;
	}
	
}
