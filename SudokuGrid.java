import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SudokuGrid {
    private int[][] grid = new int[9][9];

    // Charger la grille depuis un fichier
    public void loadFromFile(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < 9; i++) {
                String line = br.readLine().trim();
                if (line == null || line.split(" ").length != 9) {
                    throw new IOException("Format de fichier incorrect");
                }
                String[] values = line.split(" ");
                for (int j = 0; j < 9; j++) {
                    int num = Integer.parseInt(values[j]);
                    if (num < 0 || num > 9)
                        throw new IOException("Valeur invalide dans la grille");
                    grid[i][j] = num;
                }
            }
        }
    }

    // Saisie manuelle de la grille
    public void readFromConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez la grille ligne par ligne (9 chiffres séparés par des espaces, 0 pour vide) :");
        for (int i = 0; i < 9; i++) {
            String line = scanner.nextLine().trim();
            String[] values = line.split(" ");
            if (values.length != 9) {
                System.out.println("Erreur : ligne invalide. Recommencez.");
                i--;
                continue;
            }
            for (int j = 0; j < 9; j++) {
                grid[i][j] = Integer.parseInt(values[j]);
            }
        }
        scanner.close();
    }

    // Afficher la grille avec des caractères de boîte
    public void display() {
        System.out.println("┌───────┬───────┬───────┐");
        for (int i = 0; i < 9; i++) {
            System.out.print("│ ");
            for (int j = 0; j < 9; j++) {
                System.out.print((grid[i][j] == 0 ? " " : grid[i][j]) + " ");
                if ((j + 1) % 3 == 0)
                    System.out.print("│ ");
            }
            System.out.println();
            if ((i + 1) % 3 == 0 && i != 8) {
                System.out.println("├───────┼───────┼───────┤");
            }
        }
        System.out.println("└───────┴───────┴───────┘");
    }

    // Vérifier si un nombre peut être placé dans une case
    public boolean isValid(int row, int col, int num) {
        // Vérifier la ligne et la colonne
        for (int i = 0; i < 9; i++) {
            if (grid[row][i] == num || grid[i][col] == num)
                return false;
        }
        // Vérifier la sous-grille 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[startRow + i][startCol + j] == num)
                    return false;
            }
        }
        return true;
    }

    // Getters/Setters
    public int getValue(int row, int col) {
        return grid[row][col];
    }

    public void setValue(int row, int col, int num) {
        grid[row][col] = num;
    }
}