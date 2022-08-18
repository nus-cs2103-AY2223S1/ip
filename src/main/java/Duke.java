import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static int todoCount = 0;
    static int deadlinesCount = 0;
    static int eventsCount = 0;


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
            if(userMsg.contains("todo ")){
                todoCount++;
                String todo = userMsg.replaceAll("todo ", "");
                ListObject newItem = new ToDo(todo, 0);
                listOfItems.add(newItem);
                System.out.println("'Tis a new sky for you to scale! Here! \n" + newItem.toString()
                        + "\nYou now have " + listOfItems.size() + " tasks to do!"
                        + "\n***********************************************************************\n");
            }

            if(userMsg.contains("deadline ")){
                deadlinesCount++;
                String deadline1 = userMsg.replaceAll("deadline ", "");
                String[] words = deadline1.split("/");
                String task = words[0];
                String deadline = words[1];
                ListObject newItem = new Deadline(task, 0, deadline);
                listOfItems.add(newItem);
                System.out.println("Mark this on your calendar! \n" + newItem.toString()
                        + "\nYou now have " + listOfItems.size() + " tasks to do!"
                        + "\n***********************************************************************\n");
            }

            if(userMsg.contains("event ")){
                eventsCount++;
                String event1 = userMsg.replaceAll("event ", "");
                String[] words = event1.split("/");
                String task = words[0];
                String event = words[1];
                ListObject newItem = new Event(task, 0, event);
                listOfItems.add(newItem);
                System.out.println("Another moment to mark... \n" + newItem.toString()
                        + "\nYou now have " + listOfItems.size() + " tasks to do!"
                        + "\n***********************************************************************\n");
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
