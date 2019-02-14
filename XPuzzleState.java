import java.util.ArrayList;

/**
 * State in a process of searching in an X puzzle object.
 */
public class XPuzzleState implements State<XPuzzleBoard> {

    XPuzzleBoard board;
    State<XPuzzleBoard> stateCameFrom;
    double cost;

    /**
     * @return board in state
     */
    @Override
    public XPuzzleBoard getData() {
        return board;
    }

    /**
     * @param other other state to be compared
     * @return true if boards inside are equal, false otherwise
     */
    @Override
    public boolean equals(State<XPuzzleBoard> other) {
        if (this.board.size != other.getData().size)
            return false;
        int[][] otherMatrix = other.getData().matrix;
        for (int i = 0; i < board.size; i++) {
            for (int j = 0; j < board.size; j++) {
                if (this.board.matrix[i][j] != otherMatrix[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Constructor.
     * @param board board
     * @param cost cost to reach state
     * @param stateCameFrom previous state
     */
    public XPuzzleState(XPuzzleBoard board, double cost, State<XPuzzleBoard> stateCameFrom) {
        this.board = board;
        this.cost = cost;
        this.stateCameFrom = stateCameFrom;
    }

    /**
     * @return cost reaching the state
     */
    @Override
    public double getCost() {
        return this.cost;
    }

    /**
     * @return previous state
     */
    @Override
    public State<XPuzzleBoard> stateCameFrom() {
        return stateCameFrom;
    }

    /**
     * @return All possible children states
     */
    @Override
    public ArrayList<State<XPuzzleBoard>> getNextStates() {
        ArrayList<XPuzzleBoard> nextPossibleBoards = board.getPossibleMoves();
        ArrayList<State<XPuzzleBoard>> nextPossibleStates = new ArrayList<>();
        for (XPuzzleBoard board : nextPossibleBoards) {
            nextPossibleStates.add(new XPuzzleState(board, this.cost + 1, this));
        }
        return nextPossibleStates;
    }

    /**
     * @return a string representation of the path walked to reach current state
     */
    @Override
    public String getPath() {
        State<XPuzzleBoard> state = this;
        StringBuilder stringBuilder = new StringBuilder();
        int differentR;
        int differentC;
        while (true) {
            if (state.stateCameFrom() == null)
                break;
            differentR = state.getData().spacePosition.x -
                    state.stateCameFrom().getData().spacePosition.x;
            differentC = state.getData().spacePosition.y -
                    state.stateCameFrom().getData().spacePosition.y;

            if (differentC == 0) {
                if (differentR > 0)
                    stringBuilder.insert(0, "U");
                if (differentR < 0)
                    stringBuilder.insert(0, "D");
            } else if (differentR == 0) {
                if (differentC > 0)
                    stringBuilder.insert(0, "L");
                if (differentC < 0)
                    stringBuilder.insert(0, "R");
            }

            state = state.stateCameFrom();
        }
        return stringBuilder.toString();
    }
}