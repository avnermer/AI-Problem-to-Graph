import java.util.PriorityQueue;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A Star Algorithm
 * Searching from start to goal using a priority queue and heuristic object injected at run time.
 * @param <T> generic type of the states
 */
public class AStar <T> implements SearchAlgorithm<T>
{
    int numberOfNodesEvaluated;
    State<T> finalState;
    private int queueInsertionSeq = 0;
    Heuristic<T> heuristic;

    /**
     * Constructor.
     * @param heuristic heuristic to be used when evaluating cost.
     */
    public AStar(Heuristic<T> heuristic)
    {
        this.numberOfNodesEvaluated = 0;
        this.heuristic = heuristic;
    }

    /**
     * An inner class for storing states in the priority queue.
     * each entry contains state, cost, and timestamp indicating insertion order.
     * @param <T> generic type of the state
     */
    private class Entry<T> implements Comparable<Entry<T>>
    {
        private State<T> state;
        private double costPlusHeuristic;
        private int timestemp;

        /**
         * Constructor
         * @param state state
         * @param costPlusHeuristic cost + Heuristic
         */
        public Entry(State<T> state, double costPlusHeuristic)
        {
            this.state = state;
            this.costPlusHeuristic = costPlusHeuristic;
            this.timestemp = queueInsertionSeq++;
        }
        @Override
        public int compareTo(Entry<T> other) {
            int result = Double.compare(this.costPlusHeuristic, other.costPlusHeuristic);
            if(result == 0)
            {
                //if equal, give priority to the one inserted earlier
                result =  Integer.compare(this.timestemp, other.timestemp);
            }
            return result;
        }
    }

    /**
     * Searches for goal state using an injected heuristic function.
     * @param searchable object which can be searched in
     * @return final state found.
     */
    @Override
    public State<T> search(Searchable<T> searchable)
    {
        State<T> goalState = searchable.getGoalState();
        State<T> initialState = searchable.getInitialState();
        PriorityQueue<Entry<T>> queue = new PriorityQueue<>();
        queue.add(new Entry<>(initialState, heuristic.calculateHeuristicCost(initialState.getData())));

        while(!queue.isEmpty())
        {
            Entry<T> entry = queue.remove();
            this.numberOfNodesEvaluated++;
            if(entry.state.equals(goalState))
            {
                this.finalState = entry.state;
                return this.finalState;
            }
            List<State<T>> childrenStates = entry.state.getNextStates();
            for (State<T> child: childrenStates)
            {
                queue.add(new Entry<>(child, child.getCost()
                        + heuristic.calculateHeuristicCost(child.getData())));
            }
        }
        return null;
    }

    /**
     * getting a a string representation of the solution.
     * @return string solution.
     */
    @Override
    public String getSolution() {
        return this.finalState.getPath() + " " + this.numberOfNodesEvaluated + " "
                + (int)this.finalState.getCost();
    }

    /**
     * @return number of nodes evaluated during search.
     */
    @Override
    public int getNumberOfNodesEvaluated()
    {
        return this.numberOfNodesEvaluated;
    }
}
