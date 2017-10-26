package ssy066.part2;

import ssy066.part1.*;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is not a very good structured class, but it helps us when creating tests for your
 * search algorithms. Update the elements in probe and return it. 
 * You updates by for example probe.stateVisitingOrder.add(...)
 * 
 * Observe, that you may not need to write to all elements for all types of search strategies
 * 
 * @author Kristofer
 *
 */
public class Probe {
	
	// add all nodes that the search visits in order. use stateVisitingOrder.add() to add at the end
	public List<State> stateVisitingOrder = new LinkedList<State>();
	
	// add the path to the goal that you found, beginning from the initial state. Only add the transition labels to
	// the path, ie. the operation names. We can then ask the graph for the transition with that name,
	// if we need it.
	public List<String> pathToGoal = new LinkedList<String>();
	
	public int costToGoal = -1;
	
	
	
	
	public int numberOfVisitedStates() {
		return stateVisitingOrder.size();
	}

	public int numberOfNodesToGoal() {
		return pathToGoal.size();
	}

	@Override
	public String toString() {
		return "Probe [stateVisitingOrder=" + stateVisitingOrder
				+ ", pathToGoal=" + pathToGoal + ", costToGoal=" + costToGoal
				+ "]";
	}
	
	
	
}
