import java.awt.Point;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * An n*n board of x puzzle game.
 * containing a matrix of squares.
 */
public class XPuzzleBoard
{
    //members
    int size;
    int[][] matrix;
    Point spacePosition;


    /**
     * Constructor
     * @param size board size
     * @param boardContentStr a string representation of the board
     */
    public XPuzzleBoard(int size, String boardContentStr)
    {
        this.size = size;
        createMatrix(boardContentStr);
    }

    /**
     * Copy Constructor
     * @param other other board to be copied
     */
    public XPuzzleBoard(XPuzzleBoard other)
    {
        this.size = other.size;
        this.matrix = new int[size][size];
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                this.matrix[i][j] = other.matrix[i][j];
            }
        }
        this.spacePosition = new Point(other.spacePosition.x, other.spacePosition.y);
    }

    /**
     * creates the matrix of squares given a string representation
     * @param stringContents string representation of the matrix
     */
    private void createMatrix(String stringContents)
    {
        matrix = new int[size][size];
        String[] stringContentsArr = stringContents.trim().split("-");

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                //get content
                int content = Integer.valueOf(stringContentsArr[i*size + j]);
                matrix[i][j] = content;
                if (content == 0)
                {
                    spacePosition = new Point(i, j);
                }
            }
        }
    }

    /**
     * validating if an index is legal
     * @param i row index
     * @param j col index
     * @return true if valid, false otherwise
     */
    public boolean isValidIndex(int i, int j)
    {
        return ! (i < 0 || j < 0 ||j >= size ||i >= size);
    }

    /**
     * creating a new board with swapped square
     * @param i row of square to be swap
     * @param j col of square to be swap
     * @return
     */
    private XPuzzleBoard createSwappedBoard(int i, int j )
    {
        //create a new copy of current board
        XPuzzleBoard b = new XPuzzleBoard(this);
        // swap square with space
        int spaceI = this.spacePosition.x;
        int spaceJ = this.spacePosition.y;
        b.matrix[spaceI][spaceJ] = this.matrix[i][j];
        b.matrix[i][j] = 0;
        b.spacePosition.x = i;
        b.spacePosition.y = j;
        return b;
    }

    /**
     * @return All possible moves from current board
     */
    public ArrayList<XPuzzleBoard> getPossibleMoves()
    {
        ArrayList<XPuzzleBoard> possibleMovesBoards = new ArrayList<>();
        //assign movable directions to adjacent squares
        int i = spacePosition.x;
        int j = spacePosition.y;
        //the one below can move up
        if(isValidIndex(i + 1, j))
        {
            XPuzzleBoard b = createSwappedBoard(i + 1, j);
            possibleMovesBoards.add(b);
        }
        //the one above can move down
        if(isValidIndex(i - 1, j))
        {
            XPuzzleBoard b = createSwappedBoard(i - 1, j);
            possibleMovesBoards.add(b);
        }
        //the right one can move left
        if(isValidIndex(i , j + 1))
        {
            XPuzzleBoard b = createSwappedBoard(i , j + 1);
            possibleMovesBoards.add(b);
        }
        //the left one can move right
        if(isValidIndex(i , j - 1))
        {
            XPuzzleBoard b = createSwappedBoard(i , j - 1);
            possibleMovesBoards.add(b);
        }
        return possibleMovesBoards;
    }
}
