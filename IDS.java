/**
 * Iterative Deepening Search.
 * Using LDFS, DFS with depth limitation, in increasing limitation until goal is found.
 * @param <T> states generic type
 */

public class IDS<T> implements SearchAlgorithm<T>
{

    int numberOfNudesEvaluetedInLsatIteration;
    int depthReached;
    State<T> finalState;

    // Constructor
    public IDS()
    {
        int numberOfNudesEvaluetedInLsatIteration = 0;
        int depthReached = -1;
    }

    /**
     * Searches for goal state, iteratively deepening.
     * @param searchable object which can be searched in
     * @return final state found.
     */

    @Override
    public State<T> search(Searchable<T> searchable) {

        State<T> result;
        LDFS<T> ldfs;
        do
        {
            this.depthReached++;
            ldfs = new LDFS<>(this.depthReached);
            result = ldfs.search(searchable);
        }while(result == null);

        this.numberOfNudesEvaluetedInLsatIteration = ldfs.getNumberOfNodesEvaluated();
        this.finalState = result;
        return this.finalState;
    }

    /**
     * @return number of nodes evaluated during search.
     */
    @Override
    public int getNumberOfNodesEvaluated()
    {
        return this.numberOfNudesEvaluetedInLsatIteration;
    }

    /**
     * getting a a string representation of the solution.
     * @return string solution.
     */
    @Override
    public String getSolution()
    {
        return this.finalState.getPath() + " " + this.numberOfNudesEvaluetedInLsatIteration + " "
                + this.depthReached;
    }
}
