import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_AS_DONE = "mark";
    private static final String COMMAND_MARK_AS_UNDONE = "unmark";


    // Linked list to store the user's tasks
    private LinkedList<Task> storedTasks;


    // Constructor
    public Duke() {
        storedTasks = new LinkedList<>();
    }


    public void listTasks() {
        String result = "Here are the tasks in your list:\n";

        for (int i = 0; i < this.storedTasks.size(); i++) {
            String line = String.format("%d. %s\n", i + 1, this.storedTasks.get(i));
            result = result.concat(line);
        }

        System.out.println(result);
    }


    public void markTaskAsDoneOrUndone(String command, int taskNumber) {
        
        String result = "";

        // Task number is 1 index, so subtract 1 to make it 0 index
        int indexNumber = taskNumber - 1;
        
        // Get the task from the linked list
        Task t = this.storedTasks.get(indexNumber);


        // Mark the task as done or undone depending on the command
        if (command.equals(COMMAND_MARK_AS_DONE)) {
            t = t.markAsDone();
            result = "Nice! I've marked this task as done:\n";
        
        } else {
            t = t.markAsUndone();
            result = "OK, I've marked this task as undone:\n";
        }

        // Store the task back in the linked list
        this.storedTasks.set(indexNumber, t);
        

        // Add the string representation of the task
        result = result.concat(String.format("%s\n", t));
        System.out.println(result);
    }


    public void addTask(String s) {
        Task t = new Task(s);
        this.storedTasks.add(t);

        String result = String.format("added: %s\n", t);
        System.out.println(result);
    }


    public void exitDuke() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }


    // Calls the relevant function based on the given command
    // Return true if need to exit program
    public boolean executeCommand(String command, Scanner sc) {

        switch (command) {

        case COMMAND_LIST:
            listTasks();
            return false;

        // Handle marking a task as done and undone in the same function
        case COMMAND_MARK_AS_DONE:
            // Fall through

        case COMMAND_MARK_AS_UNDONE:
            markTaskAsDoneOrUndone(command, sc.nextInt());
            return false;

        
        case COMMAND_EXIT:
            exitDuke();
            return true;
            
        default:
            // Get the rest of the line in case there are multiple words
            command = command.concat(sc.nextLine());
            addTask(command);
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

            // User's command can be identified by the first token
            String command = sc.next();

            // If need to exit
            if (executeCommand(command, sc)) {
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
