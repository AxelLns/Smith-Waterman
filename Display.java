import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Display {
    public void welcome_screen(){
        System.out.println("\n██████╗░███╗░░██╗░█████╗░       ░█████╗░░█████╗░███╗░░░███╗██████╗░░█████╗░██████╗░░█████╗░████████╗░█████╗░██████╗░");
        System.out.println("██╔══██╗████╗░██║██╔══██╗       ██╔══██╗██╔══██╗████╗░████║██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗");
        System.out.println("██║░░██║██╔██╗██║███████║       ██║░░╚═╝██║░░██║██╔████╔██║██████╔╝███████║██████╔╝███████║░░░██║░░░██║░░██║██████╔╝");
        System.out.println("██║░░██║██║╚████║██╔══██║       ██║░░██╗██║░░██║██║╚██╔╝██║██╔═══╝░██╔══██║██╔══██╗██╔══██║░░░██║░░░██║░░██║██╔══██╗");
        System.out.println("██████╔╝██║░╚███║██║░░██║       ╚█████╔╝╚█████╔╝██║░╚═╝░██║██║░░░░░██║░░██║██║░░██║██║░░██║░░░██║░░░╚█████╔╝██║░░██║");
        System.out.println("╚═════╝░╚═╝░░╚══╝╚═╝░░╚═╝       ░╚════╝░░╚════╝░╚═╝░░░░░╚═╝╚═╝░░░░░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝\n");
    }

    //Function to simulate of waiting and display a message
    public void scanning() throws InterruptedException {
        System.out.print("DNA scanning, please wait professor");
        for (int i = 0; i < 4; i++){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    //Function for display the bank
    public void bank_sequence(ArrayList<String> sequences){
        String[] little_seq;

        //display the sequences
        for (int i = 0; i < sequences.size(); i++) {
            little_seq = sequences.get(i).split("");
            System.out.printf(Const.white +">random sequence %d consisting of %d bases.\n\t", i + 1, little_seq.length); // Display sequence and base number
            System.out.println(Const.white +sequences.get(i)); // display the DNA sequence
        }
    }

}
