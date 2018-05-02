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
	private Strategy h;

	/**
	 * Constructor for Search class
	 */
	public Search() {
		this.h = new Heuristic();
	}

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
	 * @precondition	 source is Sydney
	 * @param map		 graph containing all nodes
	 * @param source	 starting node
	 * @return		     list of nodes forming a path from source to the goal state
	 * @postcondition    returns the optimum path from the source to the goal state
	 */

	public List<Node> getPath(Graph map, Node source) {

		PriorityQueue<State> mapStates = new PriorityQueue<State>();								// Queue of all states searched ordered by each state's fScores
		Map<Node, List<Node>> shipmentsList = new HashMap<Node, List<Node>>(map.getShipments());	// map between shipment source and destination

		// gets the goal state of the map
		State goalState = new State(0, 0, new ArrayList<Node>(), shipmentsList);
		((Heuristic)h).heuristicSetup(goalState);

		// gets the initial state of the map
		int gScore = 0;												 // cost of path of the current state
		int heuristic = h.getHeuristic(null, goalState);    // estimated cost from current state to goal state
		int fScore = gScore + heuristic;
		State initialState = new State(gScore, fScore, new ArrayList<Node>(), new HashMap<Node, List<Node>>());
		initialState.addNode(source);
		mapStates.add(initialState);

		int nodesExpanded = 0;    // number of states taken off the queue

		// finds the state satisfying the goal state
		while (!mapStates.isEmpty()) {
			State currentState = mapStates.poll();
			nodesExpanded++;

			// checks if current state satisfies the goal state
			if (checkGoalState(goalState.getShipmentsMade(), currentState)) {
				System.out.print(nodesExpanded + " nodes expanded");
				System.out.print("\ncost = " + currentState.getGscore());
				return currentState.getPath();
			}

			// gets the new state for each current node's edge and add it to the priority queue
			for (Edge e : currentState.currentNode().getEdges()) {
				Node curr = currentState.currentNode();
				gScore = currentState.getGscore() + e.getCost() + curr.getRefuelTime();
				State newState = new State(gScore, fScore, currentState.getPath(), currentState.copyShipmentsMade());

				// curr is currently not a shipment source for a shipment made
				if (!newState.isShipmentSource(curr)) {
					// if curr is a shipment source for a shipment required but edge isnt a shipment then continue
					if (goalState.isShipmentSource(curr) && !goalState.checkShipmentsMade(curr, e.getNode())) continue;
				} else {
					// if there are more shipment destinations for curr but shipment already made for edge or the edge isnt a shipment then continue
					if (goalState.getShipmentsTo(curr).size() > newState.getShipmentsTo(curr).size()) {
						if (!goalState.checkShipmentsMade(curr, e.getNode()) || newState.checkShipmentsMade(curr, e.getNode())) continue;
						// if edge destination not smallest shipment destination continue
						if (e.getNode() != getSmallestShipmentTo(shipmentsList, curr, newState)) continue;
					}
				}

				// check if a shipment
				if (goalState.checkShipmentsMade(curr, e.getNode())) newState.addNewShipment(curr, e.getNode());

				// calculate fScore
				heuristic = h.getHeuristic(newState, goalState);
				fScore = gScore + heuristic;
				newState.setFscore(fScore);

				newState.addNode(e.getNode());
				mapStates.add(newState);
			}
		}

		return null;
	}

	// get smallest shipment destination not already made

	/**
	 * Returns smallest shipment destination for the shipment source that havent formed the shipment
	 * @precondition          shipmentFrom is a shipment source
	 * @param shipmentList    list of shipments required
	 * @param shipmentFrom	  source node of a shipment
	 * @param current		  current state of the map
	 * @return				  node
	 * @postcondition         returns smallest shipment destination for given shipment source
	 */
	public Node getSmallestShipmentTo(Map<Node, List<Node>> shipmentList, Node shipmentFrom, State current) {
		Node smallest = null;
		int smallestCost = Integer.MAX_VALUE;
		for (Node shipmentTo : shipmentList.get(shipmentFrom)) {
			// if the current shipmentTo is shipment destination continue
			if (current.checkShipmentsMade(shipmentFrom, shipmentTo)) continue;
			int currentCost = shipmentTo.getEdgeCost(shipmentFrom) + shipmentTo.getRefuelTime();
			if (smallestCost > currentCost) {
				smallestCost = currentCost;
				smallest = shipmentTo;
			}
		}
		return smallest;
	}
}
