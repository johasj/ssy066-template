package ssy066.part2;

import ssy066.part1.State;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by kristofer on 03/12/14.
 */
public interface Graph {

    public HashSet<State> getStates();

    /**
     *
     * @return all transitions of this graph
     */
    HashSet<Transition> getTransitions();

    /**
     *
     * @return the initial state of this graph
     */
    State getInitalState();

    /**
     * Given a state, returns all outgoing transitions from this state, i.e. all transition where
     * the state is a tail.
     *
     * @param s The state
     * @return All outgoing transitions
     */
    HashSet<Transition> getOutGoingTransitions(State s);

    /**
     * Given a state, return all transitions coming into that state, i.e. all transitions where
     * the state is head
     *
     * @param s the state
     * @return a set including the incoming transitions
     */
    HashSet<Transition> getIncomingTransitions(State s);

    /**
     * Returns all successor states that is reachable from state state using one transition. All states that are heads where
     * state state is tail
     *
     * @param s the state
     * @return all successor state
     */
    HashSet<State> getSuccStates(State s);

    /**
     * Returns all predecessor states that can reach state state using one transition. All states that are tails where
     * state state is head
     *
     * @param s the state
     * @return all predecessor state
     */
    HashSet<State> getPredStates(State s);

    /**
     * Returns all states that does not have any incoming transitions
     * @return A set of states
     */
    HashSet<State> getSourceStates();

    /**
     * Returns all states that does not have any outgoing transitions
     * @return A set of states
     */
    HashSet<State> getSinkStates();


    /**
     * Given a sequence of transition labels, return the state you reach when following these states. If the sequence
     * can not be followed, return null.
     *
     * @param sequence The given sequence
     * @return the reached state, or null if nothing is reached.
     */
    State getStateFromSequence(List<String> sequence);
}
