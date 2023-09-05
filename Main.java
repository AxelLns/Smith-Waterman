import java.util.Objects;
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

        //Initialisation of the menu
        boolean exit=false;
        String option;
        int indexSequence1 = -1, indexSequence2 = -1;

        System.out.println("\nWhen you want to choose a option please put the number associate");
        do{
            System.out.println("\nYou got different options:\n1- Use 2 sequences of the file\n2- Use 1 sequence from the file and 1 of your own creation\n3- Use 2 sequences from your creation\n4- See the file\n5- Exit");
            option=sc.nextLine();

            if(Objects.equals(option, "5")){
                exit=true;
            }

            if(Objects.equals(option, "1")){
                System.out.print("Choose numbers you want:\n");

                //print le fichier

                do{
                    System.out.print("First one:\n");
                    indexSequence1 = getIndexSequence(sequences, sc, indexSequence1);
                    System.out.print("Second one:\n");
                    indexSequence2 = getIndexSequence(sequences, sc, indexSequence2);
                }while(indexSequence1>10 && indexSequence2>10);
                System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), sequences.get(indexSequence2));
                // Run the Smith-Waterman algorithm on two sequences and print the result
                System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), sequences.get(indexSequence2)));
                String trash=sc.nextLine();
            }

            if(Objects.equals(option,"2")){
                System.out.print("Choose the sequence you want from the file:\n");

                //print le fichier

                do{
                    indexSequence1 = getIndexSequence(sequences, sc, indexSequence1);
                }while(indexSequence1>10);

                System.out.print(Const.white + "Please write your own second sequence: \n");
                String trash = sc.nextLine();
                String newSequence = sc.nextLine();
                System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), newSequence);
                System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), newSequence));
            }

            if(Objects.equals(option,"3")){

                System.out.print(Const.white + "Please write your own first sequence: \n");
                String newSequence1 = sc.nextLine();
                System.out.print("Please write your own second sequence :\n");
                String newSequence2 = sc.nextLine();
                System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", newSequence1, newSequence2);
                System.out.printf(Const.white + "%d", Algorithms.SmithWatermanAlgorithm(newSequence1, newSequence2));

            }

            if(Objects.equals(option,"4")){

                //print the file
                ///////////////////////////////////////////////////////////////////////////////////
                //bloquer les minuscules et les lettres qui ne sont pas de la DNA
                ///////////////////////////////////////////////////////////////////////////////////

            }

        }while (exit!=true);
        System.out.println("Thank you see you later");
        sc.close();
    }


    private static int getIndexSequence(ArrayList<String> sequences, Scanner sc, int indexSequence) {
        do {
            if (sc.hasNextInt()) {
                indexSequence = sc.nextInt() - 1;  // Convert from 1-based to 0-based index
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