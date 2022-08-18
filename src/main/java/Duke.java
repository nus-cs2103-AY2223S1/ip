import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static class ListObject{
        private String task;
        private int status;

        public ListObject(String task, int status){
            this.task = task;
            this.status = status;
        }

        public String getTask(){
            return this.task;
        }

        public int getStatus(){
            return this.status;
        }

        public String showStatusIndicator(){
            if(this.status==1){
                return "[X] ";
            } else {
                return "[ ] ";
            }
        }

        public void switchStatus(){
            if(this.status==1){
                this.status=0;
            } else {
                this.status=1;
            }
        }
    }

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

        while(true){
            Scanner readinput = new Scanner(System.in);
            String userMsg = readinput.nextLine();
            if(!userMsg.equals(showList)) {
                ListObject newItem = new ListObject(userMsg, 0);
                listOfItems.add(newItem);
                System.out.println("added: " + newItem.getTask() + "\n***********************************************************************\n");
            } else {
                for(int i = 0; i < listOfItems.size(); i++){
                    int numb = i+1;
                    ListObject currItem = listOfItems.get(i);
                    System.out.println(numb + "." + currItem.showStatusIndicator() + currItem.getTask());
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
        makeList();
    }
}
