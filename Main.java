import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Initialize an ArrayList to store DNA sequences
        ArrayList<String> sequences = new ArrayList<>();

        // Read the DNA sequences from file
        sequences = readFile();


        // Ask the user which sequences to compare
        Scanner sc = new Scanner(System.in);
        System.out.println("\n\nWhat sequences do you want to compare?");
        int a = -1, b = -1;
        do {
            if (sc.hasNextInt()) {
                a = sc.nextInt() - 1;  // Convert from 1-based to 0-based index
                if (a < 0 || a >= sequences.size()) {
                    System.out.printf("Please enter an int from %d to %d\n", 1, sequences.size());
                }
            } else {
                System.out.printf("Please enter an int from %d to %d\n", 1, sequences.size());
                sc.next();  // Clear the scanner buffer
            }
        } while (a < 0 || a >= sequences.size());

        do {
            if (sc.hasNextInt()) {
                b = sc.nextInt() - 1;  // Convert from 1-based to 0-based index
                if (b < 0 || b >= sequences.size()) {
                    System.out.printf("Please enter an int from %d to %d\n", 1, sequences.size());
                }
            } else {
                System.out.printf("Please enter an int from %d to %d\n", 1, sequences.size());
                sc.next();  // Clear the scanner buffer
            }
        } while (b < 0 || b >= sequences.size());
        sc.close();


        System.out.printf("\nYou chose sequences %s and %s\n\n", sequences.get(a), sequences.get(b));



        // Run the Smith-Waterman algorithm on two sequences and print the result
        System.out.printf("%d", SmithWatermanAlgorithm(sequences.get(a), sequences.get(b)));


    }

    // Method to read DNA sequences from a file
    public static ArrayList<String> readFile() {
        String path = "./DNA_data.txt";
        File file = new File(path);
        ArrayList<String> sequences = new ArrayList<>();
        Scanner sc = null;

        try {
            sc = new Scanner(file);
            // Loop through each line in the file
            while (sc.hasNextLine()) {
                sequences.add(sc.nextLine()); // Add each line to the ArrayList
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found"); // Handle "file not found" exception
        } finally {
            if (sc != null) {
                sc.close(); // Close the scanner to free resources
            }
        }

        //display the sequences
        for (int i = 0; i < sequences.size(); i++) {
            System.out.printf(">random sequence %d consisting of %d bases.\n\t", i + 1, sequences.size());
            System.out.println(sequences.get(i));
        }

        return sequences;
    }

    //Smith-Waterman algorithm
    public static int SmithWatermanAlgorithm(String sequence1, String sequence2){
        // Scoring system
        int match = 1;
        int mismatch = 0;
        int gap = -2;

        int max = 0; // Variable to hold the maximum score

        // Initialize the matrix
        int[][] matrix = new int[sequence1.length() + 1][sequence2.length() + 1];

        // Initialize first row and first column with 0
        for (int i = 0; i < sequence1.length() + 1; i++) matrix[i][0] = 0;
        for (int j = 0; j < sequence2.length() + 1; j++) matrix[0][j] = 0;

        // Loop through the sequences to populate the matrix
        for (int i = 1; i < sequence1.length() + 1; i++) {
            for (int j = 1; j < sequence2.length() + 1; j++) {

                // Check for a match or mismatch and update matrix
                if (sequence1.toCharArray()[i - 1] == sequence2.toCharArray()[j - 1]) {
                    matrix[i][j] = Math.max(0, Math.max(matrix[i - 1][j - 1] + match, Math.max(matrix[i - 1][j] + gap, matrix[i][j - 1] + gap)));
                } else {
                    matrix[i][j] = Math.max(0, Math.max(matrix[i - 1][j - 1] + mismatch, Math.max(matrix[i - 1][j] + gap, matrix[i][j - 1] + gap)));
                }

                // Update max score
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }

        //display the matrix
        for (int i = 0; i < sequence1.length() + 1; i++) {
            System.out.print("| ");
            for (int j = 0; j < sequence2.length() + 1; j++) {
                System.out.print(matrix[i][j] + " | ");
            }
            System.out.println();
        }

        return max; // Return maximum score
    }
}
