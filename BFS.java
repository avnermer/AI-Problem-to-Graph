import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Breadth First Search Algorithm.
 * searching from start to goal using a queue, which gives us a level based search.
 * @param <T> generic type of the states
 */

public class BFS<T> implements SearchAlgorithm<T>
{
    int numberOfNodesEvaluated;
    State<T> finalState;

    //Constructor
    public BFS()
    {
        this.numberOfNodesEvaluated = 0;
    }

    /**
     * Searches for goal state.
     * @param searchable object which can be searched in
     * @return final state found.
     */

    @Override
    public State<T> search(Searchable searchable)
    {
        State<T> goalState = searchable.getGoalState();
        Queue<State<T>> queue = new LinkedList<>();
        queue.add(searchable.getInitialState());

        while(!queue.isEmpty())
        {
            State<T> state = queue.remove();
            this.numberOfNodesEvaluated++;
            if(state.equals(goalState))
            {
                this.finalState = state;
                return this.finalState;
            }
            List<State<T>> childrenStates = state.getNextStates();
            for (State<T> child: childrenStates)
            {
                queue.add(child);
            }

        }
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
        return this.finalState.getPath() + " " + this.numberOfNodesEvaluated + " 0";
    }
}
