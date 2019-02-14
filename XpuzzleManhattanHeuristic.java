import static java.lang.Math.abs;

/**
 * A heuristic function for X puzzle.
 * Calculates the sum of Manhattan distance of each square from it's goal position
 */
public class XpuzzleManhattanHeuristic implements Heuristic<XPuzzleBoard>
{
    /**
     * calculates the heuristic given the current state's matrix
     * @param from current state's matrix
     * @return heuristic cost
     */
    @Override
    public double calculateHeuristicCost(XPuzzleBoard from) {
        int manhattanSum = 0;
        for(int i = 0; i < from.size; i++)
        {
            for(int j = 0; j < from.size; j++)
            {
                int a =  from.matrix[i][j];
                if(a == 0)
                    continue;
                manhattanSum += abs(i - (a -1)/from.size) +
                        abs(j - (a -1)%from.size);
            }
        }
        return manhattanSum;
    }
}
