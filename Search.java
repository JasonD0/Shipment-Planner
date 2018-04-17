import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Search implements SearchAlgorithm 
{
	public List<Node> getPath(Node source, Node destination) {
		PriorityQueue<Node> toExplore = new PriorityQueue<Node>(11, new Comparator<Node>() {
			public int compare(Node i, Node j) {
				if (i.getFscore() > j.getFscore()) return 1;
				if (i.getFscore() < j.getFscore()) return -1;
				return 0;
			}
			
		});
			
		HashSet<Node> visited = new HashSet<Node>();
		Map<Node, Node> successors = new HashMap<Node, Node>();
		Node current = null;
		
		toExplore.add(source);
		source.setGscore(0);
		source.setHeuristic();
		source.setFscore();
		
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
				int tentative_gScore = current.getGscore() + current.getEdgeCost(neighbour.getNodeTo()) /*+ current.getRefuelTime()*/;
				if (tentative_gScore >= neighbour.getNodeTo().getGscore()) continue; // bad path
				//System.out.println(current.getGscore() + " " + neighbour.getNodeTo().getGscore());
				
				// good path
				successors.put(neighbour.getNodeTo(), current);
				neighbour.getNodeTo().setGscore(tentative_gScore);
				neighbour.getNodeTo().setHeuristic();
				neighbour.getNodeTo().setFscore();
				//if (!toExplore.contains(neighbour.getNodeTo())) toExplore.add(neighbour.getNodeTo());
				toExplore.remove(neighbour.getNodeTo());
				toExplore.add(neighbour.getNodeTo());
			}
		}
		return null;
	}
	
	public List<Node> reconstructPath(Map<Node, Node> successors, Node current) {
		List<Node> path = new LinkedList<Node>();
		path.add(current);
		for (Map.Entry<Node, Node> parent : successors.entrySet()) {
			//System.out.println(current.getName());
			//System.out.println(parent.getKey().getName() + " " + parent.getValue().getName());
			current = parent.getValue();
			if (!path.contains(current)) path.add(current); 	
		}/*
		for (Node node : path) {
			System.out.println(node.getName());
		}*/
		return path;
	}
}
