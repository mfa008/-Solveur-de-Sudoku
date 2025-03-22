import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SudokuGrid grid = new SudokuGrid();
        SudokuSolver solver = new SudokuSolver();

        try {
            if (args.length > 0) {
                grid.loadFromFile(args[0]);
            } else {
                grid.readFromConsole();
            }

            System.out.println("\nGrille initiale :");
            grid.display();

            if (solver.solve(grid)) {
                System.out.println("\nGrille résolue :");
                grid.display();
            } else {
                System.out.println("Aucune solution trouvée.");
            }

        } catch (IOException e) {
            System.err.println("Erreur : " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Erreur : format de nombre invalide.");
        }
    }
}