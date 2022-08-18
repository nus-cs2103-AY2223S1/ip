import java.util.ArrayList;
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
                System.out.println("Ah! And so we part here today.\n We may yet meet again...Farewell, my friend!");
                break;
            }
        }
    }

    /**
     * Method to add user input to existing list and display list in reponse to "list" command
     */
    public static void addAndList(){

        ArrayList<String> listOfItems = new ArrayList<>();
        String showList = "list";

        while(true){
            Scanner readinput = new Scanner(System.in);
            String userMsg = readinput.nextLine();
            if(!userMsg.equals(showList)) {
                listOfItems.add(userMsg);
                System.out.println("added: " + userMsg + "\n***********************************************************************\n");
            } else {
                for(int i = 0; i < listOfItems.size(); i++){
                    int numb = i+1;
                    System.out.println(numb + ". " + listOfItems.get(i));
                }
                System.out.println("\n***********************************************************************\n");
            }
        }
    }

    /**
     * Main method run to converse with Duke Aemon of Old.
     */
    public static void main(String[] args) {
        introduceDuke();
        addAndList();
    }
}
