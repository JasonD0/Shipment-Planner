import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import java.util.PriorityQueue;
import java.util.HashMap;

/**
 * Search class implements the A star search algorithm
 * @invariant map follows the triangle inequality, shipments are distinct and not null
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Search
{
	/**
	 * Checks if the state given satisfies the goal state of the algorithm
	 * @param shipments       map representing the shipments completed
	 * @param currentState    state produced by the A star algorithm
	 * @return				  true if goal state satisfied
	 * 						  false otherwise
	 * @postcondition 		  checks if the goal state has been satisfied
	 */
	private boolean checkGoalState(Map<Node, List<Node>> shipments, State currentState) {
		for (Map.Entry<Node, List<Node>> shipmentsRequired : shipments.entrySet()) {
			Node shipmentFrom = shipmentsRequired.getKey();
			for (Node shipmentTo : shipmentsRequired.getValue()) {
				if (!currentState.checkShipmentsMade(shipmentFrom, shipmentTo)) return false;
			}
		}
		return true;
	}

	/**
	 * Implements the A star algorithm and returns the path from source to the goal state
	 * @param map			graph containing all nodes
	 * @param source		starting node
	 * @return				list of nodes forming a path from source to the goal state
	 * @postcondition       returns the optimum path from the source to the goal state
	 */

	public List<Node> getPath(Graph map, Node source) {

			// INSTEAD OF MAP NODE LIST  -> MAP NODE MAP   -> MAP ->sihpmentTo int -> 1/0
		// note to self : pq -> when poll -> gets lowest -> so when add order wont change
		PriorityQueue<State> mapStates = new PriorityQueue<State>();	// Queue of all states searched ordered by each state's fScores
		Map<Node, List<Node>> shipmentsList = new HashMap<Node, List<Node>>(map.getShipments());	// map between shipment source and destination
		Heuristic h = new Heuristic();
		h.heuristicSetup(shipmentsList);
		int nodesExpanded = 0;    // number of states taken off the queue

		// gets the initial state of the map
		State initialState = new State(0, h.getHeuristic(new HashMap<Node, List<Node>>()), new ArrayList<Node>(), new HashMap<Node, List<Node>>());
		initialState.addNode(source);
		mapStates.add(initialState);

		int i = 0;
		// finds the state satisfying the goal state
		while (!mapStates.isEmpty()) {
			State currentState = mapStates.poll();
			nodesExpanded++;

			if ( i == 100 ) {
				System.out.println(nodesExpanded + " nodes expanded");
				System.out.println("cost = " + currentState.getGscore());
				currentState.showPath();
				return currentState.getPath();
			} else {
				i++;
			}
			/*
				// checks if current state satisfies the goal state
				if (checkGoalState(shipmentsList, currentState) {
					currentState.showPath();
					return currentState.getPath();
				}
			*/
			// gets the new state for each current node's edge and add it to the priority queue
			for (Edge e : currentState.getCurrentNode().getEdges()) {
				// heuristic -> makes less shipments made -> lower PQ -> ie less wasted time
				int gScore = currentState.getGscore() + e.getCost() + currentState.getCurrentNode().getRefuelTime();
				State newState = new State(gScore,gScore + h.getHeuristic(currentState.getShipmentsMade()), currentState.getPath(), currentState.copyShipmentsMade());  // add value of herustic -> cost of all shipments left (change if time) -> so give state
				// check if a shipment has been made
				if (shipmentsList.get(newState.getCurrentNode()).contains(e.getNode())) {
					newState.addNewShipment(newState.getCurrentNode(), e.getNode());
					newState.setFscore(gScore + h.getHeuristic(newState.getShipmentsMade()));
				}
				newState.addNode(e.getNode());
				// PROB IF SHIPMENTS -> ADD ONLY SHIPMENTS -> SO DONT ADD IF NO SHIPMENT -> DONT DO THIS IF SHIPMENT ALREADY MADE
					// can do this B/C TRIANGLE INEQ
				mapStates.add(newState);
			}
		}

		return null;
	}
}
