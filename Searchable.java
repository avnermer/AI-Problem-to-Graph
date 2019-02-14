/**
 * An interface for objects which can be searched or solved.
 * @param <T> generic type of state
 */

public interface Searchable <T> {
    /**
     * @return initial state of the searchable
     */
    State<T> getInitialState();

    /**
     * @return goal state of the searchable
     */
    State<T> getGoalState();
}
