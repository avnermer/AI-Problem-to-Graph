import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.exit;

/**
 * Main class, reading the instructions from input.txt file.
 * intended to solve the X puzzle game
 * Writes solution to output.txt file
 */
public class java_ex1 {

    /**
     * main function.
     * intended to solve the X puzzle game
     * @param args
     */
    public static void main(String[] args)
    {
        int algorithmID = 0;
        SearchAlgorithm<XPuzzleBoard> searchAlgorithm = null;
        int boardSize = 0;
        String initialStateStr = null;

        //reading instructions
        try (BufferedReader br = new BufferedReader(new FileReader("input.txt")))
        {
            String line;

            if ((line = br.readLine()) != null)
            {
                algorithmID = Integer.valueOf(line.trim());
            }
            if ((line = br.readLine()) != null)
            {
                boardSize = Integer.valueOf(line.trim());
            }
            if ((line = br.readLine()) != null)
            {
                initialStateStr = line.trim();
            }
        } catch (IOException e) {
            e.printStackTrace();
            exit(0);
        }

        switch(algorithmID)
        {
            case 1 :
                searchAlgorithm = new IDS();
                break;
            case 2 :
                searchAlgorithm = new BFS();
                break;
            case 3:
                searchAlgorithm = new AStar<>(new XpuzzleManhattanHeuristic());
        }

        //searching
        State<XPuzzleBoard> result = searchAlgorithm.search(new XPuzzleGame(boardSize, initialStateStr));
        /*if(result == null)
        {
            System.out.println("No solution");
        }*/

        //writing solution to output file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt")))
        {
            bw.write(searchAlgorithm.getSolution());
        } catch (IOException e) {
        e.printStackTrace();
        exit(0);
    }
    }
}
