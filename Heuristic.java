/**
 * calculating a generic heuristic cost given a state.
 * Should be familiar with the searchable and goal state.
 * Should be Admissible and easy to calculate.
 * @param <T> generic type of the data inside the problem \ state
 */
public interface Heuristic<T> {
    /**
     * calculates the heuristic given the current state's data
     * @param from current state's data
     * @return heuristic cost
     */
    double calculateHeuristicCost(T from);
}

