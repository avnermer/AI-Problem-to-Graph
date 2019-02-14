import java.util.List;

/**
 * State in a process of searching in  a searchable object.
 * @param <T> generic type of the data stored in the state
 */
public interface State<T>
{
    /**
     * @return data in state
     */
    T getData();

    /**
     * @return cost reaching the state
     */
    double getCost();

    /**
     * @return All possible children states
     */
    List<State<T>> getNextStates();

    /**
     * @return previous state
     */
    State<T> stateCameFrom();

    /**
     * @return a string representation of the path walked to reach current state
     */
    String getPath();

    /**
     * @param other other state to be compared
     * @return true if equal, false otherwise
     */
    boolean equals(State<T> other);
}
