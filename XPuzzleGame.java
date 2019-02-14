/**
 * A problem of arranging a non - ordered board of numbers
 * given an initial order.
 */

public class XPuzzleGame implements Searchable<XPuzzleBoard> {

    XPuzzleState initialState;
    int boardSize;

    /**
     * Constructor.
     * @param boardSize board Size
     * @param initialboardContentStr a string representation of the initial state
     */
    public XPuzzleGame(int boardSize, String initialboardContentStr) {
        this.boardSize = boardSize;
        this.initialState = new XPuzzleState(new XPuzzleBoard(boardSize, initialboardContentStr),
                0, null);
    }

    /**
     * @return initial state of the board
     */
    @Override
    public State<XPuzzleBoard> getInitialState() {
        return this.initialState;
    }

    /**
     * @return goal state of the board, where numbers are ordered incrementally
     */
    @Override
    public State<XPuzzleBoard> getGoalState() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < this.boardSize; i++)
        {
            //prepare goal state string representation
            for(int j = 0; j <this.boardSize; j++)
            {
                if(i == this.boardSize -1 && j == this.boardSize -1)
                {
                    stringBuilder.append(String.valueOf(0));
                    break;
                }
                stringBuilder.append(String.valueOf(i * this.boardSize + j + 1)).append("-");
            }
        }
        return new XPuzzleState(new XPuzzleBoard(boardSize, stringBuilder.toString()),
                0, null);
    }
}
