import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
        String path = "./DNA_data.txt";
        File file = new File(path);
        try {
            Scanner sc = new Scanner(file);
            System.out.println("File found");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
