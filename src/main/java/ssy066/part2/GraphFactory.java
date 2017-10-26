package ssy066.part2;

import java.util.*;

import ssy066.part1.*;

/**
 * A Factory that makes graphs.
 *
 * Created by kristofer on 21/11/14.
 */
public class GraphFactory {

    /**
     * Given a set of operations and a state, return the operations that are enabled, i.e. their condition evaluate true
     *
     * This method are used when creating a graph in makeMeAGraph
     *
     * @param ops All operations in the system
     * @param s The state
     * @return
     */
    public static HashSet<Operation> enabledOperations(Set<Operation> ops, State s) {
        throw new NotImplementedException();
    }

    /**
     * Given a set of operations and a state, return all outgoing transitions based on the
     * enabled operations in the state tail. The name of each transition should be the name
     * of the operation.
     * @param ops All the operations in the system
     * @param tail Current state
     * @return All outgoing transitions
     */
    public static HashSet<Transition> makeTransitions(Set<Operation> ops, State tail){
        throw new NotImplementedException();
    }

    /**
     * This is a so called factory method. It makes graphs.
     * Creates a Graph based on a set of operations and a initial state. If you divide this method
     * into multiple parts, do not forget to make your methods in this file public static.
     * Remember that you must create your own implementation of the interface Graph
     * @param operations All the operations in the system
     * @param init The initial state
     * @return The graph
     */
	public static Graph makeMeAGraph(Set<Operation> operations, State init){
	    throw new NotImplementedException();
    }



}
