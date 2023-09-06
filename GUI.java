import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class GUI {
    private JFrame frame;
    private JTextField sequence1Field;
    private JTextField sequence2Field;
    private JTextField matchField;
    private JTextField mismatchField;
    private JTextField gapField;
    private JTextPane outputArea;
    private JTable outputTable;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                GUI window = new GUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public GUI() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 550, 610);
        frame.setTitle("Smith-Waterman Algorithm");
        try {
            // Initialize an Image object with the path to your icon
            Image icon = Toolkit.getDefaultToolkit().getImage("img/DNA.jpg");

            // Set the window icon
            frame.setIconImage(icon);
        } catch (Exception e) {
            e.printStackTrace();
        }

        frame.setPreferredSize(new Dimension(550, 610));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use FlowLayout
        frame.getContentPane().setLayout(new FlowLayout());

        JLabel lblSeq1 = new JLabel("  Enter Sequence 1 : ");
        frame.getContentPane().add(lblSeq1);

        sequence1Field = new JTextField();
        sequence1Field.setColumns(10);
        frame.getContentPane().add(sequence1Field);
        sequence1Field.setText("GTCGATTTGA");

        JLabel lblSeq2 = new JLabel("  Enter Sequence 2 : ");
        frame.getContentPane().add(lblSeq2);

        sequence2Field = new JTextField();
        sequence2Field.setColumns(10);
        frame.getContentPane().add(sequence2Field);
        sequence2Field.setText("ACGAAAGAGG");

        JLabel lblMatch = new JLabel("  Enter match value : ");
        frame.getContentPane().add(lblMatch);

        matchField = new JTextField();
        matchField.setColumns(10);
        frame.getContentPane().add(matchField);
        matchField.setText("1");

        JLabel lblMismatch = new JLabel("  Enter mismatch value : ");
        frame.getContentPane().add(lblMismatch);

        mismatchField = new JTextField();
        mismatchField.setColumns(10);
        frame.getContentPane().add(mismatchField);
        mismatchField.setText("0");

        JLabel lblGap = new JLabel("                                  Enter gap value : ");
        frame.getContentPane().add(lblGap);

        gapField = new JTextField();
        gapField.setColumns(10);
        frame.getContentPane().add(gapField);
        gapField.setText("-2");

        JLabel pad = new JLabel("                                ");
        frame.getContentPane().add(pad);

        JButton btnRun = new JButton("Run Smith-Waterman");
        frame.getContentPane().add(btnRun);

        outputTable = new JTable() {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        outputTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        outputTable.setDragEnabled(false);
        outputTable.setRowSelectionAllowed(false);
        outputTable.setCellSelectionEnabled(false);
        outputTable.setShowGrid(true);
        outputTable.setGridColor(Color.BLACK);
        outputTable.setRowHeight(20);
        outputTable.setFont(new Font("Monospaced", Font.PLAIN, 18));
        outputTable.setTableHeader(null);

        frame.getContentPane().add(outputTable);

        // Wrap the JTextPane in a JScrollPane to make it scrollable
        JScrollPane scrollPane = new JScrollPane(
                outputTable,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, // Enable vertical scrollbar when needed
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED // Enable horizontal scrollbar when needed
        );

        frame.getContentPane().add(scrollPane);

        outputArea = new JTextPane();
        outputArea.setEditable(false);
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        outputArea.setPreferredSize(new Dimension(470, 20)); // Set preferred size
        frame.getContentPane().add(outputArea);

        btnRun.addActionListener(e -> runSmithWaterman());

    }

    private void runSmithWaterman() {
        String seq1 = sequence1Field.getText().toUpperCase();
        String seq2 = sequence2Field.getText().toUpperCase();

        String match = matchField.getText();
        String mismatch = mismatchField.getText();
        String gap = gapField.getText();

        if (seq1.equals("") || seq2.equals("") || match.equals("") || mismatch.equals("") || gap.equals("")) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

            int[][] matrix = new int[seq1.length() + 1][seq2.length() + 1];

            // Convert int[][] to Object[][] for DefaultTableModel
            Object[][] tableData = new Object[matrix.length + 1][matrix[0].length + 1];

            int score = Algorithms.SmithWatermanAlgorithm(seq1, seq2, matrix, Integer.parseInt(match),
                    Integer.parseInt(mismatch), Integer.parseInt(gap));

            // Populate the table data
            tableData[0][0] = "S";
            for (int i = 1; i <= seq1.length(); i++) {
                tableData[i + 1][0] = String.valueOf(seq1.charAt(i - 1));
            }
            for (int j = 1; j <= seq2.length(); j++) {
                tableData[0][j + 1] = String.valueOf(seq2.charAt(j - 1));
            }
            for (int i = 1; i <= matrix.length; i++) {
                for (int j = 1; j <= matrix[0].length; j++) {
                    tableData[i][j] = matrix[i - 1][j - 1];
                }
            }

            // Create a new DefaultTableModel
            DefaultTableModel tableModel = new DefaultTableModel(tableData, tableData[0]);

            // Update JTable data
            outputTable.setModel(tableModel);

            // Set Custom Renderer for Cell Coloring
            outputTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                Color[] colors = { Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA, Color.CYAN, Color.RED };
                Color black = Color.BLACK;
                Color gray = Color.GRAY;
                Color white = Color.WHITE;

                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus,
                        int row, int column) {
                    super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    // Set first row and first column background to black and font to white
                    if (row == 0 || column == 0) {
                        setBackground(gray);
                        setForeground(white);
                    } else {
                        setBackground(white); // Reset to default background color
                        if (value != null && value instanceof Integer) {
                            int cellValue = (Integer) value;
                            setForeground(cellValue == 0 ? black : colors[cellValue % colors.length]);
                        } else {
                            setForeground(black);
                        }
                    }

                    return this;
                }
            });

            // Adjust column widths
            for (int j = 0; j < tableData[0].length; j++) {
                TableColumn column = outputTable.getColumnModel().getColumn(j);
                column.setPreferredWidth(25);
            }

            // Output the score elsewhere (e.g., another JTextArea, JLabel, etc.)
            outputArea.setText("Score: " + score);
        }
    }
}