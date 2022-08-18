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
     * Method to add user input to existing list and display list in response to "list" command and mark items as complete
     */
    public static void makeList(){

        ArrayList<ListObject> listOfItems = new ArrayList<>();
        String showList = "list";
        String markAsDone = "mark ";
        String markAsNotDone = "unmark ";

        while(true){
            Scanner readinput = new Scanner(System.in);
            String userMsg = readinput.nextLine();

            if(userMsg.equals(showList)){
                System.out.println("These are the tasks on your list!");
                for(int i = 0; i < listOfItems.size(); i++){
                    int numb = i+1;
                    ListObject currItem = listOfItems.get(i);
                    System.out.println(numb + "." + currItem.toString());
                }
                System.out.println("\n***********************************************************************\n");
            }

            if(userMsg.contains(markAsDone) && !userMsg.contains(markAsNotDone)){
                String taskNo = userMsg.replaceAll("\\D+", "");
                int taskNoAsInt = Integer.parseInt(taskNo)-1;
                ListObject currItem = listOfItems.get(taskNoAsInt);
                currItem.switchStatus();
                System.out.println("Very well! One less burden to bear! I have marked this complete:\n" + currItem.toString());

                System.out.println("\n***********************************************************************\n");

            }
            if(userMsg.contains(markAsNotDone)){

                String taskNo = userMsg.replaceAll("\\D+", "");
                int taskNoAsInt = Integer.parseInt(taskNo)-1;
                ListObject currItem = listOfItems.get(taskNoAsInt);
                currItem.switchStatus();
                System.out.println("Hmm....I have marked this incomplete:\n" + currItem.toString());
                System.out.println("\n***********************************************************************\n");

            }
            if(!userMsg.equals(showList)&&!userMsg.contains(markAsDone) && !userMsg.contains(markAsNotDone)){
                ListObject newItem = new ListObject(userMsg, 0);
                listOfItems.add(newItem);
                System.out.println("added: " + newItem.getTask() + "\n***********************************************************************\n");
            }
        }
    }


    /**
     * Main method run to converse with Duke Aemon of Old.
     */
    public static void main(String[] args) {
        introduceDuke();
        makeList();
    }
}
