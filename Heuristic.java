import java.util.Map;
import java.util.List;

/**
 * Calculates the estimate cost of the current state's progress towards the goal State
 *      Using the estimate cost of shipments made
 * @invariant shipments are distinct and not null
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Heuristic implements Strategy
{
    /**
     * Returns the heuristic value for a particular state
     * @precondition       goalState != null, currentState != null
     * @param currState    map representing shipments that haven't been completed
     * @return             estimate to reaching the goal state from the current state
     * @postcondition      returns the heuristic value for a given state
     */
    @Override
    public int getHeuristic(State currState, State goalState) {
        if (currState == null) return 0;
        int goalStateProgressCost = 0;

        // sums total cost of shipments made
        for (Map.Entry<Node, List<Node>> shipments : currState.getShipmentsMade().entrySet()) {
            // add refuel time for shipment source
            goalStateProgressCost += (shipments.getKey().getRefuelTime()) * shipments.getValue().size();
            for (Node shipmentTo : shipments.getValue()) {
                goalStateProgressCost += shipments.getKey().getEdgeCost(shipmentTo);
                // add refuel time for shipment destinations if not shipment source (wont double count)
                if (!goalState.isShipmentSource(shipmentTo))  goalStateProgressCost += shipmentTo.getRefuelTime() + currState.getLowestEdgeCost(shipmentTo);
            }
        }
        return goalStateProgressCost*-1;
    }
}
