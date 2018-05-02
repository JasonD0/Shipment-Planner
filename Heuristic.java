import java.util.Map;
import java.util.List;

/**
 * Calculates the estimate cost of the cheapest path from current state to goal state
 * @invariant shipments are distinct and not null
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Heuristic implements Strategy
{
    private int initialHeuristic;

    /**
     * Returns the heuristic value for a particular state
     * @precondition       goalState != null, currentState != null
     * @param currState    map representing shipments that haven't been completed
     * @return             estimate to reaching the goal state from the current state
     * @postcondition      returns the heuristic value for a given state
     */
    @Override
    public int getHeuristic(State currState, State goalState) {
        if (currState == null) return this.initialHeuristic;
        int goalStateProgressCost = 0;

        // sums total cost of shipments made
        for (Map.Entry<Node, List<Node>> shipments : currState.getShipmentsMade().entrySet()) {
            // add refuel time for shipment source
            goalStateProgressCost += (shipments.getKey().getRefuelTime()) * shipments.getValue().size();
            for (Node shipmentsTo : shipments.getValue()) {
                goalStateProgressCost += shipments.getKey().getEdgeCost(shipmentsTo);
                // add refuel time for shipment destinations if not shipment source (wont double count)
                if (!goalState.isShipmentSource(shipmentsTo))  goalStateProgressCost += shipmentsTo.getRefuelTime();
            }
        }
        return this.initialHeuristic - goalStateProgressCost;
    }

    /**
     * Sets initial heuristic value
     * @precondition       goalState != null
     * @param goalState    goal state of the search algorithm
     * @postcondition      sets initial heuristic value
     */
    public void heuristicSetup(State goalState) {
        int goalStateCost = 0;
        // sums total cost of shipments required
        for (Map.Entry<Node, List<Node>> shipments : goalState.getShipmentsMade().entrySet()) {
            // add refuel time for shipment source
            goalStateCost += (shipments.getKey().getRefuelTime()) * shipments.getValue().size();
            for (Node shipmentsTo : shipments.getValue()) {
                goalStateCost += shipments.getKey().getEdgeCost(shipmentsTo);
                // add refuel time for shipment destinations if not shipment source (wont double count)
                if (!goalState.isShipmentSource(shipmentsTo)) goalStateCost += shipmentsTo.getRefuelTime();
            }
        }
        this.initialHeuristic = goalStateCost;
    }
}
