import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


// maybe set of must pass as parameter -> if direct connection -> go there 
// maybe call astarseach multiple times for each shipment

public class AStarSearch 
{
	public List<Node> getPath(Node source, Node destination, int mapSize) {
		PriorityQueue<Node> toExplore = new PriorityQueue<Node>();  // need implement comparator -> in node(?) somwhere
		HashSet<Node> visited = new HashSet<Node>();
		Map<Node, Node> successors = new HashMap<Node, Node>();
		
		// can do for primitive types eg new Double/Integer/etc (0)
		Map<Node, Integer> gScore = new HashMap<Node, Integer>();  // SELF NOTE: Double(primitive) parameter can be null, double can't (similar with int)
		Map<Node, Integer> fScore = new HashMap<Node, Integer>();
		Node current = null;
		toExplore.add(source);
		gScore.put(source, 0);
		fScore.put(source, 0/*replace by heurstic function*/);
		
		while (!toExplore.isEmpty()) {
			current = toExplore.poll();
			if (current.equals(destination)) {     // probably just do current.name/id  destination....   or do own equals method ...(best)
				return reconstructPath(successors, destination);
			}
			
			toExplore.remove(current);  // redundant but keep so follow pseudo code -> remove once working at end
			visited.add(current);
			
			for (Edge neighbour : current.getEdges()) {
				if (visited.contains(neighbour.getNodeTo())) continue; 
				
				// dont add refuel time when put gscore -> b/c extra if else here (ie if first loop -> dont add fuel)
				int tentative_gScore = gScore.get(current) + current.getEdgeCost(neighbour.getNodeTo()) + current.getRefuelTime();
				if (tentative_gScore >= gScore.get(neighbour.getNodeTo())) continue; // bad path
				
				// good path
				successors.put(neighbour.getNodeTo(), current);
				gScore.put(neighbour.getNodeTo(), tentative_gScore);
				fScore.put(neighbour.getNodeTo(), gScore.get(neighbour.getNodeTo()) + 0/*replace by heuristic function*/);
				if (!toExplore.contains(neighbour.getNodeTo())) toExplore.add(neighbour.getNodeTo());
			}
		}
		return null;
	}
	
	public List<Node> reconstructPath(Map<Node, Node> successors, Node current) {
		List<Node> path = new LinkedList<Node>();
		path.add(current);
		for (Map.Entry<Node, Node> parent : successors.entrySet()) {
			current = parent.getKey();
			path.add(current); 	
		}
		return path;
	}
}
