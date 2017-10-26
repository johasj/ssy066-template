package ssy066.part2;

import ssy066.part1.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Use this to store the state together with the path to it and the cost
 * @author Kristofer
 *
 */
class PimpedState {
	public final State state;
	private final List<String> pathToState;
	private final int costToState;
	
	public PimpedState(State state, List<String> pathToState, int costToState){
		this.state = state;
		this.pathToState = new LinkedList<String>(pathToState);
		this.costToState = costToState;
	}
	
	/**
	 * This represent the labels on the transitions taken to reach this state
	 * @return
	 */
	public List<String> pathToState() {
		return new LinkedList<>(pathToState);
	}
	
	
	public int cost() {
		return costToState;
	}






}
