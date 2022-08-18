import java.util.Scanner;

public class Duke {

    /**
     * Method to print Duke's self-introduction and to customise its personality.
     */
    public static void introduceDuke(){
        String intro = "I once wandered these halls, centuries ago. I am Duke Aemon of Old.\n";
        String quote1 = "Indeed, my memory is long when I am but a ghost of a memory myself..." +
                "\nBut you are young blood. What brings you to these ancient halls?"
                +"\n***********************************************************************";

        System.out.println("Welcome, my friend!\n" + intro + quote1);
    }

    /**
     * Method to exit chat if user types "bye" and print user input otherwise.
     */
    public static void echoAndExit(){
        String exitCmd = "bye";
        while(true){
            Scanner readinput = new Scanner(System.in);
            String userMsg = readinput.nextLine();
            if(!userMsg.equals(exitCmd)) {
                System.out.println(userMsg + "\n" + "***********************************************************************\n");
            } else {
                System.out.println("Ah! And so we part here today. \n We may yet meet again. Farewell, my friend!");
                break;
            }
        }
    }

    /**
     * Main method run to converse with Duke Aemon of Old.
     */
    public static void main(String[] args) {
        introduceDuke();
        echoAndExit();
    }
}
