import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    // Method to read DNA sequences from a file
    public static ArrayList<String> readFile() {
        String path = "./src/DNA_data.txt";
        File file = new File(path);
        ArrayList<String> sequences = new ArrayList<>();

        try (Scanner sc = new Scanner(file)) {
            // Loop through each line in the file
            while (sc.hasNextLine()) {
                sequences.add(sc.nextLine()); // Add each line to the ArrayList
            }
        } catch (FileNotFoundException e) {
            System.out.println(Const.white +"File not found"); // Handle "file not found" exception
        }
        // Close the scanner to free resources

        //display the sequences
        for (int i = 0; i < sequences.size(); i++) {
            System.out.printf(Const.white +">random sequence %d consisting of %d bases.\n\t", i + 1, sequences.size());
            System.out.println(Const.white +sequences.get(i));
        }

        return sequences;
    }
}
