import javax.sound.midi.Sequence;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {
        ArrayList<String> Sequences = new ArrayList<String>();
        Sequences = readFile();
        System.out.printf("%d", SmithWatermanAlgorithm(Sequences.get(6), Sequences.get(1)));


    }
    public static ArrayList<String> readFile() {
        String path = "./DNA_data.txt";
        File file = new File(path);
        ArrayList<String> sequence = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
            System.out.println("File found");
            while (sc.hasNextLine()) {
                sequence.add(sc.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            if (sc != null) {
                sc.close(); // Close the scanner to free resources
            }
        }
        return sequence;
    }


    public static int SmithWatermanAlgorithm(String Sequence1, String Sequence2){
        int match = 1;
        int mismatch = 0;
        int gap = -2;
        int max = 0;
        int[][] matrix = new int[Sequence1.length() + 1][Sequence2.length() + 1];
        for (int i = 0; i < Sequence1.length() + 1; i++) {
            matrix[i][0] = 0;
        }
        for (int j = 0; j < Sequence2.length() + 1; j++) {
            matrix[0][j] = 0;
        }
        for (int i = 1; i < Sequence1.length() + 1; i++) {
            for (int j = 1; j < Sequence2.length() + 1; j++) {
                if (Sequence1.toCharArray()[i -1] == Sequence2.toCharArray()[j - 1]) {
                    matrix[i][j] = Math.max(0, Math.max(matrix[i - 1][j - 1] + match, Math.max(matrix[i - 1][j] + gap, matrix[i][j - 1] + gap)));
                } else {
                    matrix[i][j] = Math.max(0, Math.max(matrix[i - 1][j - 1] + mismatch, Math.max(matrix[i - 1][j] + gap, matrix[i][j - 1] + gap)));
                }
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                }
            }
        }
        return max;
    }
}
