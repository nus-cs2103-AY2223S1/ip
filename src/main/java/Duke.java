import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";


    // Linked list to store the user's strings
    private LinkedList<String> storedStrings;


    // Constructor
    public Duke() {
        storedStrings = new LinkedList<>();
    }


    public void listStrings() {
        String result = "";

        for (int i = 0; i < this.storedStrings.size(); i++) {
            String line = String.format("%d. %s\n", i, this.storedStrings.get(i));
            result = result.concat(line);
        }

        System.out.println(result);
    }


    public void addString(String s) {
        this.storedStrings.add(s);

        String result = String.format("added: %s\n", s);
        System.out.println(result);
    }


    public void exitDuke() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }


    // Calls the relevant function based on the given command
    // Return true if need to exit program
    public boolean executeCommand(String command) {

        switch (command) {

        case LIST_COMMAND:
            listStrings();
            return false;        

        case EXIT_COMMAND:
            exitDuke();
            return true;
            
        default:
            addString(command);
            return false;
        }
    }


    private void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");
    }


    public void processCommands() {

        Scanner sc = new Scanner(System.in);

        while (true) {

            String command = sc.nextLine();

            // If need to exit
            if (executeCommand(command)) {
                sc.close();
                return;
            }
        }
    }



    public static void main(String[] args) {
        
        Duke d = new Duke();

        // Print the logo
        d.initialize();

        // Process user commands
        d.processCommands();


    }
}
