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
	 * @param source		starting node 
	 * @param destination	ending node 
	 * @return				list of nodes forming a path from source to destination
	 */
	public List<Node> getPath(Graph map, Node source, Node destination) {
		State state = new State(0);
		Map<Node, List<Node>> shipments = map.getShipments();
		state.initShipmentsDone(shipments);
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
		
		/*
		PriorityQueue<State> mapStates = new PriorityQueue<State>();			// Queue containing a path searched	
		// instead of Map for both closed and allmapstates -> add node element in constructor -> last node
		List<State> visited = new ArrayList<State>();							// List of paths visited
		Map<Node, List<Node>> shipments = map.getShipments();
		
		State initialState = new State(0);		// initially fscore is 0
		initialState.addNode(source);			// add source to state path
		mapStates.add(initialState);
		
		// get state where path lowest and contains shipment
		while (!mapStates.isEmpty()) {												// while OPEN is not empty
			State currentState = mapStates.poll();									// remove <n, p> from head of queue (minimal f(n))
			visited.add(currentState);												//  add <n, p> to CLOSED
			if (checkGoalState(shipments, currentState)) return currentState.getPath();		// if n is a goal state return success path p
			for (Edge e : currentState.getCurrentNode().getEdges()) {				// for each edge e from n to n� (with cost c, so g(n�) = g(n) + c)
				State newState = new State(currentState.getFscore());				// new state for each edge possibility
				newState.addPath(currentState.getPath());			
				newState.addNode(e.getNode());
				mapStates.add(newState);
			}
		}*/
		
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
