import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


// maybe set of must pass as parameter -> if direct connection -> go there 
// maybe call astarseach multiple times for each shipment

public class AStarSearch<E> 
{
	public List<Node<E>> getPath(Node<E> source, Node<E> destination, int mapSize) {
		PriorityQueue<Node<E>> toExplore = new PriorityQueue<Node<E>>(); 
		HashSet<Node<E>> visited = new HashSet<Node<E>>();
		Map<Node<E>, Node<E>> successors = new HashMap<Node<E>, Node<E>>();
		
		// can do for primitive types eg new Double/Integer/etc (0)
		Map<Node<E>, Integer> gScore = new HashMap<Node<E>, Integer>();  // SELF NOTE: Double(primitive) parameter can be null, double can't (similar with int)
		Map<Node<E>, Integer> fScore = new HashMap<Node<E>, Integer>();
		Node<E> current = null;
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
			
			for (Edge<E> neighbour : current.getEdges()) {
				if (visited.contains(neighbour.getNodeTo())) continue; 
				if (!toExplore.contains(neighbour.getNodeTo())) toExplore.add(neighbour.getNodeTo());
				
				// dont add refuel time when put gscore -> b/c extra if else here (ie if first loop -> dont add fuel)
				int tentative_gScore = gScore.get(current) + current.getEdgeCost(destination) + current.getRefuelTime();
				if (tentative_gScore >= gScore.get(neighbour.getNodeTo())) continue; // bad path
				
				// good path
				successors.put(neighbour.getNodeTo(), current);
				gScore.put(neighbour.getNodeTo(), tentative_gScore);
				fScore.put(neighbour.getNodeTo(), gScore.get(neighbour.getNodeTo()) + 0/*replace by heuristic function*/);
			}
		}
		return null;
	}
	
	public List<Node<E>> reconstructPath(Map<Node<E>, Node<E>> successors, Node<E> current) {
		List<Node<E>> path = new LinkedList<Node<E>>();
		path.add(current);
		for (Map.Entry<Node<E>, Node<E>> parent : successors.entrySet()) {
			current = parent.getKey();
			path.add(current); 	
		}
		return path;
	}
}
