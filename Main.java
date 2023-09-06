import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Initialize an ArrayList to store DNA sequences
        ArrayList<String> sequences = new ArrayList<>();

        //Read the DNA sequences from file
        sequences = Reader.readFile();

        //Ask the user which sequences to compare
        Scanner sc = new Scanner(System.in);

        //Initialization of display class and display welcome screen
        Display affichage = new Display();
        affichage.welcome_screen();

        //Initialisation of the menu
        boolean exit=false;
        String option;
        int indexSequence1 = -1, indexSequence2 = -1;

        //Start the principal loop
        System.out.println("\nWhen you want to choose a option please put the number associate");
        do{
            System.out.println("\nYou got different options:\n" +
                    "1- Use 2 sequences of the file\n" +
                    "2- Use 1 sequence from the file and 1 of your own creation\n" +
                    "3- Use 2 sequences from your creation\n" +
                    "4- See the file\n" +
                    "5- Add a sequence of DNA to the bank\n" +
                    "6- Exit");
            option=sc.nextLine();

            //condition to leave the principal loop
            if(Objects.equals(option, "6")){
                exit=true;
            }

            //The option of taking two sequences of the bank
            if(Objects.equals(option, "1")){
                System.out.print("Choose numbers you want:\n");

                //Display the sequences to let choose the user
                affichage.bank_sequence(sequences);

                do{
                    System.out.print("First one:\n");
                    indexSequence1 = getIndexSequence(sequences, sc, indexSequence1);
                    System.out.print("Second one:\n");
                    indexSequence2 = getIndexSequence(sequences, sc, indexSequence2);
                }while(indexSequence1>10 && indexSequence2>10);

                //short waiting screen to make a little suspense
                affichage.scanning();
                System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), sequences.get(indexSequence2));
                //Run the Smith-Waterman algorithm on two sequences and print the result
                int score=Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), sequences.get(indexSequence2), new int[sequences.get(indexSequence1).length() + 1][sequences.get(indexSequence2).length() + 1], 1, 0, -2);
                System.out.println("The final score is "+score);
                String trash=sc.nextLine();
            }

            //The option of taking one sequence of the bank and create one
            if(Objects.equals(option,"2")){

                //Display the sequences
                affichage.bank_sequence(sequences);

                System.out.print("Choose the sequence you want from the file:\n");
                //Choice of the one of the bank
                do{
                    indexSequence1 = getIndexSequence(sequences, sc, indexSequence1);
                }while(indexSequence1>10);

                //Creation of the user's sequence
                String trash = sc.nextLine();
                String newSequence;
                int temp=0;
                do{ //Do the security for the DNA letters
                    System.out.printf(Const.white +"Please write your own second sequence (is only available with : A,C,G,T)\n");
                    newSequence=sc.nextLine();
                    temp=sequence(newSequence);
                }while(temp!=1);

                //short waiting screen to make a little suspense
                affichage.scanning();
                System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", sequences.get(indexSequence1), newSequence);
                int score=Algorithms.SmithWatermanAlgorithm(sequences.get(indexSequence1), newSequence, new int[sequences.get(indexSequence1).length() + 1][newSequence.length() + 1], 1, 0, -2);
                System.out.println("The final score is "+score);
            }

            //The option of creating two sequences
            if(Objects.equals(option,"3")){

                String newSequence1;
                int temp=0;
                do{
                    System.out.printf(Const.white +"Please write your own first sequence (is only available with : A,C,G,T)\n");
                    newSequence1=sc.nextLine();
                    temp=sequence(newSequence1);
                }while(temp!=1);

                String newSequence2;
                temp=0;
                do{
                    System.out.printf(Const.white +"Please write your own second sequence (is only available with : A,C,G,T)\n");
                    newSequence2=sc.nextLine();
                    temp=sequence(newSequence2);
                }while(temp!=1);

                //short waiting screen to make a little suspense
                affichage.scanning();
                System.out.printf(Const.white + "\nYou chose sequences %s and %s\n\n", newSequence1, newSequence2);
                int score=Algorithms.SmithWatermanAlgorithm(newSequence1, newSequence2, new int[newSequence1.length() + 1][newSequence2.length() + 1], 1, 0, -2);
                System.out.println("The final score is "+score);
            }

            //The option of just display sequences
            if(Objects.equals(option,"4")){
                affichage.bank_sequence(sequences);
            }

            if (Objects.equals(option,"5")){
                String newSequence1;
                int temp=0;
                do{
                    System.out.printf(Const.white + "Please write your own sequence (is only available with : A,C,G,T)\n");
                    newSequence1=sc.nextLine();
                    temp=sequence(newSequence1);
                }while(temp!=1);
                Write.write(newSequence1);

                //Read again the DNA sequences from file with the new sequence added
                sequences = Reader.readFile();
            }

        }while (exit!=true);
        System.out.println("Thank you see you later");
        sc.close();
    }

    private static int sequence(String newSequence){
        int pass=0;
            for(int i=0;i<newSequence.length();i++){
                if (newSequence.charAt(i)=='A' || newSequence.charAt(i)=='C' || newSequence.charAt(i)=='G' || newSequence.charAt(i)=='T'){
                    pass+=1;
                }
                else{
                    pass=0;
                }
            }
        if (pass==newSequence.length()){
            return 1;
        }
        return 0;
    }

    //This function gets the index of the sequence
    private static int getIndexSequence(ArrayList<String> sequences, Scanner sc, int indexSequence) {
        do {
            if (sc.hasNextInt()) {
                indexSequence = sc.nextInt() - 1;  //Convert from 1-based to 0-based index. Due to the start at 0 in java in an array
                if ((indexSequence < 0 || indexSequence >= sequences.size()) && indexSequence != 10) {
                    System.out.printf(Const.white + "Please enter an int from %d to %d\n", 1, sequences.size());
                }
            } else {
                System.out.printf(Const.white + "Please enter an int from %d to %d\n", 1, sequences.size());
                sc.next();  //Clear the scanner buffer
            }
        } while ((indexSequence < 0 || indexSequence >= sequences.size()) && indexSequence != 10);
        return indexSequence;
    }
}

