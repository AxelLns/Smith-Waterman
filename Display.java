import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Display {
    public static void waiting() {
        System.out.println("\nPress enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {
            System.out.println(e);
        }
        clear();
    }

    public static void clear(){
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (IOException | InterruptedException e) {
            System.out.println(e);
        }
        System.out.println("\n██████╗░███╗░░██╗░█████╗░       ░█████╗░░█████╗░███╗░░░███╗██████╗░░█████╗░██████╗░░█████╗░████████╗░█████╗░██████╗░");
        System.out.println("██╔══██╗████╗░██║██╔══██╗       ██╔══██╗██╔══██╗████╗░████║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗");
        System.out.println("██║░░██║██╔██╗██║███████║       ██║░░╚═╝██║░░██║██╔████╔██║██████╔╝███████║██████╔╝███████║░░░██║░░░██║░░██║██████╔╝");
        System.out.println("██║░░██║██║╚████║██╔══██║       ██║░░██╗██║░░██║██║╚██╔╝██║██╔═══╝░██╔══██║██╔══██╗██╔══██║░░░██║░░░██║░░██║██╔══██╗");
        System.out.println("██████╔╝██║░╚███║██║░░██║       ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░░░░░██║░░██║██║░░██║██║░░██║░░░██║░░░╚█████╔╝██║░░██║");
        System.out.println("╚═════╝░╚═╝░░╚══╝╚═╝░░╚═╝       ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝\n");
    }

    //Function to simulate of waiting and display a message
    public static void scanning() throws InterruptedException {
        System.out.print("DNA scanning, please wait professor");
        for (int i = 0; i < 4; i++){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    //Function for display the bank
    public static void bank_sequence(ArrayList<String> sequences){
        String[] little_seq;

        //display the sequences
        for (int i = 0; i < sequences.size(); i++) {
            little_seq = sequences.get(i).split("");
            System.out.printf(Const.white +">random sequence %d consisting of %d bases.\n\t", i + 1, little_seq.length); // Display sequence and base number
            System.out.println(Const.white +sequences.get(i)); // display the DNA sequence
        }
    }

}
