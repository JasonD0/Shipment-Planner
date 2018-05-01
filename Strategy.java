import java.util.List;
import java.util.Map;

/**
 * Strategy pattern interface to supply a heuristic to the search procedure
 * @author Jason Do
 * COMP2511
 * Assignment 2 Shipment Planner
 */

public interface Strategy
{
    public int getHeuristic(State currState, State goalState);
}
