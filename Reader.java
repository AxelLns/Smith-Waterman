import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    //A method to read DNA sequences from a file
    public static ArrayList<String> readFile() {
        String path = "./DNA_data.txt"; //The path of the document .txt who stock the DNA sequences
        File file = new File(path);
        ArrayList<String> sequences = new ArrayList<>(); //Initialization of the object "sequences" to be a resizable array

        try (Scanner sc = new Scanner(file)) { //Initialization of "sc" object who contain the whole DNA.txt read by scanner
            //Loop through each line in the file
            while (sc.hasNextLine()) { //Verify if "sc" contain a line of data
                sequences.add(sc.nextLine()); //Add each line to the ArrayList
            }

        } catch (FileNotFoundException e) {
            System.out.println(Const.white +"File not found"); //Handle "file not found" exception
        }

        return sequences;
    }
}
