/**
 * An interface for search algorithms, specifically graph based
 * working with states, returning a string representation of the solution.
 * @param <T> generic type of the state
 */
public interface SearchAlgorithm<T>
{
    /**
     * Searches for goal state.
     * @param searchable object which can be searched in
     * @return final state found.
     */
    State<T> search(Searchable<T> searchable);

    /**
     * getting a a string representation of the solution.
     * @return string solution.
     */
    String getSolution();

    /**
     * @return number of nodes evaluated during search.
     */
    int getNumberOfNodesEvaluated();
}
