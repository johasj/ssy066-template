package ssy066.part2;

import junit.framework.TestCase;
import ssy066.part1.*;

import java.util.*;

/**
 * Based on the graph implementation, it is time to try out search strategies. Tip, use your 
 * new knowledge from when you created the graph.
 *
 * The optimization only need to handle DAGs, so there will be no direct loops.
 * 
 * 
 * @author Kristofer
 *
 */
public class Test4_Optimization extends TestCase {
	
	// these are used in the tests and are initialized in the setUp() method. You do no not 
	// need to change it.
	private String v = "v";
	private State testState;
	
	private Predicate vEq0 = new EQ(v, 0);
	private Predicate vEq1 = new EQ(v, 1);
	private Predicate vEq2 = new EQ(v, 2);
	private Predicate vEq3 = new EQ(v, 3);
	private Predicate vEq4 = new EQ(v, 4);
	
	private Action vInc = new NextInc(v,1);
	private Action vDec = new NextDec(v,1);
	private Action vInc2 = new NextInc(v,2);
	private Action vTo4 = new Next(v,4);
	private Action vTo6 = new Next(v,6);
	private Action vTo7 = new Next(v,7);
	                    
	private Operation o1 = new Operation("o1", vEq0, Arrays.asList(vInc), 1);
	private Operation o2 = new Operation("o2", vEq0, Arrays.asList(vInc2), 3);
	private Operation o3 = new Operation("o3", vEq1, Arrays.asList(vInc2), 4);
	private Operation o4 = new Operation("o4", vEq1, Arrays.asList(vTo4), 2);
	private Operation o5 = new Operation("o5", vEq3, Arrays.asList(vInc2), 1);
	private Operation o6 = new Operation("o6", vEq4, Arrays.asList(vInc), 3);
	private Operation o7 = new Operation("o7", vEq4, Arrays.asList(vInc2), 3);
	private Operation o8 = new Operation("o8", vEq2, Arrays.asList(vTo6), 5);
	private Operation o9 = new Operation("o9", vEq2, Arrays.asList(vTo7), 10);
	
	private Set<Operation> operations;
	
	private Graph g;
	
	private List<State> testSeq012;
	private List<State> testSeq021;
	private List<String> shortestPath;
	private List<String> longPath;

    SearchAGraph searchAGraph;
	
	
	/**
	 * This is the setup method for the variables that are used. This is run before each test.
	 */
	protected void setUp() {
	
		Map<String, Object> initial = new HashMap<String, Object>();
		initial.put(v, 0); 
		testState = new State(initial);
		
		operations =  new HashSet<Operation>();
		operations.add(o1); operations.add(o2);operations.add(o3);operations.add(o4);operations.add(o5);
		operations.add(o6); operations.add(o7);operations.add(o8);operations.add(o9);

		g = GraphFactory.makeMeAGraph(operations, testState);
		
		// Setting up the test. Matching on the visiting state order in probe since that is
		// different for BFS and DFS
		testSeq012 = new LinkedList<State>();
		testSeq012.add(g.getInitalState());
		testSeq012.add(testState.newState(v, 1));
		testSeq012.add(testState.newState(v, 2));
		
		testSeq021 = new LinkedList<State>();
		testSeq021.add(g.getInitalState());
		testSeq021.add(testState.newState(v, 2));
		testSeq021.add(testState.newState(v, 1));
		
		shortestPath = new LinkedList<String>();
		shortestPath.add(o2.name);
		shortestPath.add(o8.name);
		
		longPath = new LinkedList<String>();
		longPath.add(o1.name);
		longPath.add(o4.name);
		longPath.add(o7.name);

        searchAGraph = new SearchAGraph();
	}
	
	
// *** TEST start below

	
	
	/**
	 * In Graph g, a new graph is created based on the operations above and their preconditions. This test is just a help for you
	 * to look at the graph printout. When you are running the test, the nodes and transitions are shown in the console below.
	 * 
	 * Add more printout for other methods in graph, for better help.
	 * 
	 * The operations and the transitions, now include cost. You can access it by calling the transition.cost() method. In the printout you will
	 * see the cost in parenthesis newState to the transition label name.
	 * 
	 * Tip: draw the graph by hand as a reference by looking at the definitions of the operations and the printout.
	 * 
	 */
	public void test1_implementAGraph() {	
		Set<State> result = g.getStates();
		Set<Transition> trans = g.getTransitions();
		
		System.out.println("States in g: "+ result);
		System.out.println("Transitions in g: "+trans);
		
		assertTrue(true);
	}
	
	/**
	 * Time to do the deep-first search. You should implement the search strategy DFS_FirstPath. read about it in SearchAGraph.java
	 * at the top of the file.
	 * 
	 * Store the visiting order of the nodes in the probe. A tip in how to implement the search is found in Graph.java and the
	 * exempleMaker method.
	 * 
	 * Observe that you should only use the methods in graph and you do not need to think about operations etc (except when
	 * evaluating the goal predicate by goal.eval(state)). 
	 * 
	 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayDeque.html can represent both a FIFI and FILO (Stack)
	 * Use methods add, push, pop to implement both algorithms
	 * 
	 */
	public void test2_deepfirstSearch() {	
		Probe p = searchAGraph.search(g, new EQ(v, 6), SearchAGraph.SearchStrategies.DFS_FirstPath);


		System.out.println("");
		System.out.println("test2: "+p);
		System.out.println("test2: stateVisitingOrder should NOT contain sequence: "+testSeq012+" or "+testSeq021);
		System.out.println("test2: path to goal can be etiher: "+longPath+" or "+shortestPath);


		assertFalse(containSubSeq(p.stateVisitingOrder, testSeq012) || containSubSeq(p.stateVisitingOrder, testSeq021));
		assertTrue(p.pathToGoal.equals(longPath) || p.pathToGoal.equals(shortestPath));
		
		// BFS_ShortestPath, DFS_ShortestPath, BFS_FirstPath, DFS_FirstPath, BFS_lowestCost, DFS_lowestCost,
	}
	
	/**
	 * Implement BFS_FirstPath. Change as little as possible in the search code.
	 */
	public void test3_breathfirstSearch() {	
		Probe p = searchAGraph.search(g, new EQ(v, 6), SearchAGraph.SearchStrategies.BFS_FirstPath);

        System.out.println("");
		System.out.println("test3: "+p);
        System.out.println("test3: stateVisitingOrder MUST contain sequence: "+testSeq012+" or "+testSeq021);
        System.out.println("test3: path to goal should be: "+shortestPath);
        System.out.println("test3: You visited "+p.numberOfVisitedStates()+" states, which must be <= 7");

		assertTrue(containSubSeq(p.stateVisitingOrder, testSeq012) || containSubSeq(p.stateVisitingOrder, testSeq021));
		assertTrue(p.pathToGoal.equals(shortestPath));
		assertTrue(p.numberOfVisitedStates() <= 7); // you should not visit the last level of nodes
		
	}

	/**
	 * Implement DFS_ShortestPath. Observe that you must guarantee the shortest path!
	 */
	public void test4_DFS_ShortestPath() {	
		Probe p = searchAGraph.search(g, new EQ(v, 6), SearchAGraph.SearchStrategies.DFS_ShortestPath);

        System.out.println("");
		System.out.println("test4: "+p);
        System.out.println("test4: stateVisitingOrder should NOT contain sequence: "+testSeq012+" or "+testSeq021);
        System.out.println("test4: path to goal should be: "+shortestPath);

		assertFalse(containSubSeq(p.stateVisitingOrder, testSeq012) || containSubSeq(p.stateVisitingOrder, testSeq021));
		assertTrue(p.pathToGoal.equals(shortestPath));
	}
	
	/**
	 * Implement BFS_ShortestPath. Observe that you must guarantee the shortest path!
	 */
	public void test5_BFS_ShortestPath() {	
		Probe p = searchAGraph.search(g, new EQ(v, 6), SearchAGraph.SearchStrategies.BFS_ShortestPath);

        System.out.println("");
		System.out.println("test5: "+p);
        System.out.println("test5: stateVisitingOrder MUST contain sequence: "+testSeq012+" or "+testSeq021);
        System.out.println("test5: path to goal should be: "+shortestPath);
        System.out.println("test5: You visited "+p.numberOfVisitedStates()+" states, which must be <= 7");
		
		assertTrue(containSubSeq(p.stateVisitingOrder, testSeq012) || containSubSeq(p.stateVisitingOrder, testSeq021));
		assertTrue(p.pathToGoal.equals(shortestPath));
		assertTrue(p.numberOfVisitedStates() <= 7); // BFS should not visit all nodes
		
	}
	
	/**
	 * Implement DFS_lowestCost. Observe that you must guarantee the shortest path!
	 */
	public void test6_DFS_lowestCost() {	
		Probe p = searchAGraph.search(g, new EQ(v, 6), SearchAGraph.SearchStrategies.DFS_lowestCost);

        System.out.println("");
		System.out.println("test6: "+p);
        System.out.println("test6: stateVisitingOrder should NOT contain sequence: "+testSeq012+" or "+testSeq021);
        System.out.println("test7: path to goal should be: "+longPath);
        System.out.println("test6: cost to goal should be: 6");

		assertFalse(containSubSeq(p.stateVisitingOrder, testSeq012) || containSubSeq(p.stateVisitingOrder, testSeq021));
		assertTrue(p.costToGoal == 6);
	}
	
	/**
	 * Implement BFS_lowestCost. Observe that you must guarantee the shortest path!
	 */
	public void test7_BFS_lowestCost() {	
		Probe p = searchAGraph.search(g, new EQ(v, 6), SearchAGraph.SearchStrategies.BFS_lowestCost);

        System.out.println("");
		System.out.println("test7: "+p);
        System.out.println("test7: stateVisitingOrder MUST contain sequence: "+testSeq012+" or "+testSeq021);
        System.out.println("test7: path to goal should be: "+longPath);
        System.out.println("test6: cost to goal should be: 6");
		
		assertTrue(containSubSeq(p.stateVisitingOrder, testSeq012) || containSubSeq(p.stateVisitingOrder, testSeq021));
		assertTrue(p.costToGoal == 6);
		
	}

	/**
	 * Testing with another graph. Should just work
	 */
	public void test8_AnotherGraphTest() {

		Operation o11 = new Operation("o1", vEq0, Arrays.asList(vInc), 1);
		Operation o21 = new Operation("o2", vEq0, Arrays.asList(vInc2), 2);
		Operation o31 = new Operation("o3", vEq1, Arrays.asList(vInc2), 1);
		Operation o41 = new Operation("o4", vEq2, Arrays.asList(vInc), 2);
		Operation o51 = new Operation("o5", vEq3, Arrays.asList(vInc2), 1);
		Operation o61 = new Operation("o6", vEq3, Arrays.asList(vInc), 20);

		Set<Operation> ops =  new HashSet<Operation>();
		ops.add(o11); ops.add(o21);ops.add(o31);ops.add(o41);ops.add(o51);
		ops.add(o61);

		Graph g2 = GraphFactory.makeMeAGraph(ops, testState);

		Probe p = searchAGraph.search(g2, new EQ(v, 4), SearchAGraph.SearchStrategies.BFS_lowestCost);

		System.out.println("Another graph");
		System.out.println("Another graph: "+p);
		System.out.println("Another graph: "+g2.getStates());
		System.out.println("Another graph: "+g2.getTransitions());

		assertTrue(p.costToGoal == 22);

	}
	
	
	
	// A small helper to find sub sequences in a list
	private boolean containSubSeq (List<State> ns, List<State> sub) {
		List<State> nsc = new LinkedList<State>(ns);
		if (nsc == null || sub == null || nsc.size() < sub.size()) return false;
		if (nsc.size() == sub.size() && nsc.equals(sub)) return true;
		
		if (sub.equals(nsc.subList(0, sub.size()))) return true;
		nsc.remove(0);
		return containSubSeq(nsc, sub);				
	}

}
