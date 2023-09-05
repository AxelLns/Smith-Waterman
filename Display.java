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

    public void scanning() throws InterruptedException {
        System.out.print("DNA scanning, please wait");
        for (int i = 0; i < 4; i++){
            System.out.print(".");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void bank_sequence(ArrayList<String> sequences){
        //display the sequences
        for (int i = 0; i < sequences.size(); i++) {
            System.out.printf(Const.white +">random sequence %d consisting of %d bases.\n\t", i + 1, sequences.size()); // Display sequence and base number
            System.out.println(Const.white +sequences.get(i)); // display the DNA sequence
        }
    }

}