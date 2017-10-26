package ssy066.part2;

import ssy066.part1.Predicate;


public class SearchAGraph {

    // type == SearchStrategies.DFS_ShortestPath,

    // type == SearchStrategiesBFS_lowestCost,
    // type == DFS_lowestCost)

    /*
     * Here are some search strategies. If you like, you can define more. All of
     * them should visit as few nodes as possible. Although, in some cases, all
     * nodes needs to be visited. Think about when and why. Return the visiting
     * order in probe stateVisitingOrder
     *
     * BFS_ShortestPath -> Uses breadth-first search and return the shortest
     * path to goal via the element pathToGoal in probe. You need to guarantee
     * that it is the shortest path!
     *
     * DFS_ShortestPath -> Uses deep-first search and return the shortest path
     * to goal via the element pathToGoal in probe. You need to guarantee that
     * it is the shortest path!
     *
     * BFS_FirstPath -> Uses breadth-first search and return the first path you
     * find to goal via the element pathToGoal in probe.
     *
     * DFS_FirstPath -> Uses deep-first search and return the first path you
     * find to goal via the element pathToGoal in probe.
     *
     * BFS_lowestCost -> Uses breadth-first search and return the lowest cost to
     * goal via the element costToGoal in probe. You should also return that
     * path
     *
     * DFS_lowestCost -> Uses deep-first search and return the lowest cost to
     * goal via the element costToGoal in probe. You should also return that
     * path.
     */
    public enum SearchStrategies {
        BFS_ShortestPath, DFS_ShortestPath, BFS_FirstPath, DFS_FirstPath, BFS_lowestCost, DFS_lowestCost,
    }




	/**
	 * This is the main method that searches a graph for a goal state. The goal
	 * state include a state that evaluates the goal predicate to true.
	 * 
	 * The enum type SearchStrategies defines what search strategy you will use.
	 * Try to minimize the differences between the various search strategies!!!
	 * It may even be so that some of the strategies are the same ;) This
	 * exercise is about thinking about the similarities and differences, so
	 * change the code even if the test passes, to make as much of the code in
	 * common as possible
	 * 
	 * The result is return in a wrapper class called Probe. Look at the code
	 * and description in probe. fill the probe p as you go along or in the end
	 * of the search. Observe that you do not need to fill all elements for all
	 * strategies.
	 * 
	 * Important: Remember to add the nodes you are visiting to
	 * p.stateVisitingOrder, where p is the probe.
	 * 
	 * I have also created a PimpedState class below. Use that if you like, to
	 * store the state you have vistited together with the path to it and its
	 * cost.
	 * 
	 * @param g
	 *            The graph. Start searching in its initial state. It does not
	 *            need to be a tree!
	 * @param type
	 *            the search strategy enum
	 * @return the probe
	 */
	public Probe search(Graph g, Predicate goal, SearchStrategies type) {
		Probe p = new Probe();
		return p;
	}

}
