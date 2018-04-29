import java.util.Map;
import java.util.List;

/**
 * Implementation of the strategy that supplies context with the heuristic cost
 * @invariant shipments are distinct and not null
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public class Heuristic implements Strategy
{
    /**
     * Returns the heuristic value for a particular state
     * @param goalStateProgress     map representing shipments that haven't been completed
     * @return                      estimate to reaching the goal state from the current state
     * @postcondition               returns the heuristic value for a given state
     */
    @Override
    public int getHeuristic(Map<Node, List<Node>> goalStateProgress) {
        int heuristic = 0;
        // sums the total cost for shipments that are to be completed
        for (Map.Entry<Node, List<Node>> shipments : goalStateProgress.entrySet()) {
            heuristic += (shipments.getKey().getRefuelTime()) * shipments.getValue().size();        // eg sydney -> manila, vancouver    -> sydney manila    and sydney vancouver   -> sydney refuel twice ie size of shipmento
            for (Node shipmentsTo : shipments.getValue()) {
                heuristic += shipments.getKey().getEdgeCost(shipmentsTo);
            }
        }
        return heuristic;
    }
}
