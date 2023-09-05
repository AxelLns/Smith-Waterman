import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.BufferedWriter;
import java.io.PrintWriter;

public class Write {
    public static void write(String new_sequence) {
        try (FileWriter f = new FileWriter("./DNA_data.txt", true);
             BufferedWriter b = new BufferedWriter(f);
             PrintWriter p = new PrintWriter(b);) {

            p.println(new_sequence);
            System.out.println("Successful addition to the DNA bank\n");
        } catch (IOException i) {
            System.out.println("An error occurred.");
        }
    }
}
