import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        // Initialize an ArrayList to store DNA sequences
        ArrayList<String> sequences = new ArrayList<>();

        // Read the DNA sequences from file
        sequences = Reader.readFile();


        // Ask the user which sequences to compare
        Scanner sc = new Scanner(System.in);
        System.out.println(Const.white + "\n\nWhat sequences do you want to compare?");
        int indexSequence1 = -1, indexSequence2 = -1;

        indexSequence1 = getIndexSequence(sequences, sc, indexSequence1);
        indexSequence2 = getIndexSequence(sequences, sc, indexSequence2);
        sc.close();

        System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), sequences.get(indexSequence2));

        // Run the Smith-Waterman algorithm on two sequences and print the result
        System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), sequences.get(indexSequence2)));
    }

    private static int getIndexSequence(ArrayList<String> sequences, Scanner sc, int indexSequence1) {
        do {
            if (sc.hasNextInt()) {
                indexSequence1 = sc.nextInt() - 1;  // Convert from 1-based to 0-based index
                if (indexSequence1 < 0 || indexSequence1 >= sequences.size()) {
                    System.out.printf(Const.white + "Please enter an int from %d to %d\n", 1, sequences.size());
                }
            } else {
                System.out.printf(Const.white + "Please enter an int from %d to %d\n", 1, sequences.size());
                sc.next();  // Clear the scanner buffer
            }
        } while (indexSequence1 < 0 || indexSequence1 >= sequences.size());
        return indexSequence1;
    }
}