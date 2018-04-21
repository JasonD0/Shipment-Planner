import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
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
	// A STAR SEARCH 36:16 lec -> change to follow that structure
	// ~48 (before that time) -> some incomplete code about a* -> try see if node/edge class shown 
	public List<Node> getPath(Node source, Node destination) {
		// A priority queue of nodes that haven't been explored in the graph
		// Nodes with lower fScores have higher priority (?)
		PriorityQueue<Node> toExplore = new PriorityQueue<Node>(11, new Comparator<Node>() {
			public int compare(Node i, Node j) {
				if (i.getFscore() > j.getFscore()) return 1;
				if (i.getFscore() < j.getFscore()) return -1;
				return 0;
			}
			
		});
			
		HashSet<Node> visited = new HashSet<Node>();				// a set of nodes visited 
		Map<Node, Node> successors = new HashMap<Node, Node>();		// a map between a parent node and it's child
		Node current = null;										// the top element of the priority queue
		int nodesExpanded = 0;										// number of nodes taken off the queue
		
		
		
		toExplore.add(source);
		source.setGscore(0);
		source.setHeuristic();
		source.setFscore();
		
		while (!toExplore.isEmpty()) {
			current = toExplore.poll();
			visited.add(current);
			nodesExpanded++;
			
			if (current.equals(destination)) {     
				return reconstructPath(successors, destination, nodesExpanded);
			}
			
			for (Edge neighbour : current.getEdges()) {
				if (visited.contains(neighbour.getNode())) continue; 
				
				// dont add refuel time when put gscore -> b/c extra if else here (ie if first loop -> dont add fuel)
				int tentative_gScore = current.getGscore() + current.getEdgeCost(neighbour.getNode()) + current.getRefuelTime();
				if (tentative_gScore >= neighbour.getNode().getGscore()) continue; // bad path
				//System.out.println(current.getGscore() + " " + neighbour.getNodeTo().getGscore());
				
				// good path
				successors.put(neighbour.getNode(), current);
				neighbour.getNode().setGscore(tentative_gScore);
				neighbour.getNode().setHeuristic();
				neighbour.getNode().setFscore();
				//if (!toExplore.contains(neighbour.getNodeTo())) toExplore.add(neighbour.getNodeTo());
				// remove and add neighbour so that the priority queue can reorder itself 
				toExplore.remove(neighbour.getNode());
				toExplore.add(neighbour.getNode());
			}
		}
		return null;
	}
	
	/**
	 * Backtrack from destination to the source node and returns the path
	 * @param successors		the map between a parent node and it's child node 
	 * @param current			the destination node of the path
	 * @param nodesExpanded		the number of nodes expanded in the search algorithm  
	 * @return					the path from source to destination
	 */
	public List<Node> reconstructPath(Map<Node, Node> successors, Node current, int nodesExpanded) {
		List<Node> path = new LinkedList<Node>();
		path.add(current);
		for (Map.Entry<Node, Node> parent : successors.entrySet()) {
			current = parent.getValue();
			if (current.equals(parent.getValue()) && path.size() > 1) continue;
			path.add(current);
		}
		System.out.print(nodesExpanded + " nodes expanded");
		return path;
	}
}
