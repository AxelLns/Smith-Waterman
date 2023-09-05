import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
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

        if (indexSequence1 <10 && indexSequence2 <10){
            System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), sequences.get(indexSequence2));
            // Run the Smith-Waterman algorithm on two sequences and print the result
            System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), sequences.get(indexSequence2)));
        } else if (indexSequence1 == 10 && indexSequence2 != 10){
            System.out.print(Const.white + "Please write your own sequence: \n");
            String trash = sc.nextLine();
            String newSequence = sc.nextLine();
            System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", newSequence, sequences.get(indexSequence2));
            System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(newSequence, sequences.get(indexSequence2)));

        } else if (indexSequence2 == 10 && indexSequence1 != 10) {
            System.out.print(Const.white + "Please write your own sequence: \n");
            String trash = sc.nextLine();
            String newSequence = sc.nextLine();
            System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), newSequence);
            System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), newSequence));
        } else {
            System.out.print(Const.white + "Please write your own sequences: \n");
            String trash = sc.nextLine();
            String newSequence1 = sc.nextLine();
            //trash = sc.nextLine();
            String newSequence2 = sc.nextLine();
            System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", newSequence1, newSequence2);
            System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(newSequence1, newSequence2));

        }
        sc.close();

    }

    private static int getIndexSequence(ArrayList<String> sequences, Scanner sc, int indexSequence) {
        do {
            if (sc.hasNextInt()) {
                indexSequence = sc.nextInt() - 1;  // Convert from 1-based to 0-based index. Due to the start at 0 in java in an array
                if ((indexSequence < 0 || indexSequence >= sequences.size()) && indexSequence != 10) {
                    System.out.printf(Const.white + "Please enter an int from %d to %d\n", 1, sequences.size());
                }
            } else {
                System.out.printf(Const.white + "Please enter an int from %d to %d\n", 1, sequences.size());
                sc.next();  // Clear the scanner buffer
            }
        } while ((indexSequence < 0 || indexSequence >= sequences.size()) && indexSequence != 10);
        return indexSequence;
    }
}