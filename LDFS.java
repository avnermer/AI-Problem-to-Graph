import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Depth First Search with depth limitation. Iterative version using stack and depth field.
 * @param <T> generic type of states
 */
public class LDFS<T> implements SearchAlgorithm<T> {

    int numberOfNodesEvaluated;
    State<T> finalState;
    int  limit;
    int depthReached;

    public LDFS(int limit)
    {
        this.numberOfNodesEvaluated = 0;
        this.limit = limit;
        this.depthReached = -1;
    }

    /**
     * Entry for storing states in stack along with depth, for iterative implementation.
     * @param <T> states generic type
     */
    private class Entry<T>
    {
        private State<T> state;
        private int depth;

        public Entry(State<T> state, int depth) {
            this.state = state;
            this.depth = depth;
        }

    }

    /**
     * Searches for goal state, return null if limitation reached without any result.
     * @param searchable object which can be searched in
     * @return final state found.
     */
    @Override
    public State<T> search(Searchable<T> searchable)
    {
        State<T> goalState = searchable.getGoalState();
        Stack<Entry<T>> stack = new Stack<>();
        State<T> initialState =searchable.getInitialState();
        stack.add(new Entry<>(initialState, 0));

        while(!stack.isEmpty())
        {
            Entry<T> currentStateDepthPair = stack.pop();
            this.numberOfNodesEvaluated++;
            if(currentStateDepthPair.state.equals(goalState))
            {
                this.finalState = currentStateDepthPair.state;
                this.depthReached = currentStateDepthPair.depth;
                return this.finalState;
            }
            if(currentStateDepthPair.depth < this.limit)
            {
                List<State<T>> childrenStates = currentStateDepthPair.state.getNextStates();

                //reverse order before pushing, so states will be popped in correct order
                Collections.reverse(childrenStates);

                for (State<T> child: childrenStates)
                {
                    stack.push(new Entry<>(child, currentStateDepthPair.depth +1));
                }
            }
        }
        this.depthReached = limit;
        return null;
    }

    /**
     * @return number of nodes evaluated during search.
     */
    @Override
    public int getNumberOfNodesEvaluated()
    {
        return this.numberOfNodesEvaluated;
    }

    /**
     * getting a a string representation of the solution.
     * @return string solution.
     */
    @Override
    public String getSolution() {
        return this.finalState.getPath() + " " + this.numberOfNodesEvaluated + " "
                + this.depthReached;
    }
}
