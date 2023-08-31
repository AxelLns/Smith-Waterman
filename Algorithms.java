public class Algorithms {
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
        System.out.print(Const.white + "|   |   | ");
        for (int i = 0; i < sequence2.length(); i++) {
            System.out.printf(Const.white + "%c | ", sequence2.toCharArray()[i]);
        }
        System.out.print(Const.white +"\n");
        for (int i = 0; i < sequence1.length() + 1; i++) {
            System.out.print(Const.white + "| ");
            if (i > 0) {
                System.out.printf(Const.white + "%c | ", sequence1.toCharArray()[i-1]);
            } else {
                System.out.print(Const.white + "  | ");
            }
            for (int j = 0; j < sequence2.length() + 1; j++) {
                System.out.print((matrix[i][j] == 0 ? Const.white : Const.colors[matrix[i][j]%6]) + matrix[i][j] + " | ");
            }
            System.out.print(Const.white + "\n");
        }

        return max; // Return maximum score
    }
}
