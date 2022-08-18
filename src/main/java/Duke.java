import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK_AS_DONE = "mark";
    private static final String COMMAND_MARK_AS_UNDONE = "unmark";
    private static final String COMMAND_ADD_TODO = "todo";
    private static final String COMMAND_ADD_DEADLINE = "deadline";
    private static final String COMMAND_ADD_EVENT = "event";


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


    public void markTaskAsDoneOrUndone(String[] commands) {
        
        String result = "";

        // First token is the action
        String action = commands[0];

        // Task number is the second token
        // Task number is 1 index, so subtract 1 to make it 0 index
        int indexNumber = Integer.parseInt(commands[1]) - 1;
        
        // Get the task from the linked list
        Task t = this.storedTasks.get(indexNumber);


        // Mark the task as done or undone depending on the command
        if (action.equals(COMMAND_MARK_AS_DONE)) {
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


    public void addTask(String[] commands) {
        // Create the correct type of task based on the first token
        Task t = createTask(commands);
        this.storedTasks.add(t);

        String result = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.\n",
                                        t, storedTasks.size());
        System.out.println(result);
    }


    public Task createTask(String[] commands) {

        final char TIME_DELIMITER = '/';
        String description = "";
        boolean isDeadline = false;

        // The first token is used to identify which type of task to create
        switch (commands[0]) {

        case COMMAND_ADD_TODO:
            // Second token onwards is the description
            description = commands[1];
            for (int i = 2; i < commands.length; i++) {
                description = description.concat(String.format(" %s", commands[i]));
            }

            return new ToDo(description);
        
        
        // Handle adding a Deadline task and an Event task the same way
        case COMMAND_ADD_DEADLINE:
            isDeadline = true;
            // Fall through

        case COMMAND_ADD_EVENT:
            // Second token until /by is the description
            description = commands[1];

            int i = 0;
            for (i = 2; i < commands.length; i++) {
                if (commands[i].charAt(0) == TIME_DELIMITER) {
                    // Stop adding tokens to the description
                    break;
                }
                description = description.concat(String.format(" %s", commands[i]));
            }

            // Skip over the delimiter token
            // The tokens after the delimiter are the date and time
            i++;
            String dateAndTime = commands[i];
            for (i++; i < commands.length; i++) {
                dateAndTime = dateAndTime.concat(String.format(" %s", commands[i]));
            }
        
            return isDeadline ? new Deadline(description, dateAndTime) : new Event(description, dateAndTime);

        
        default:
            return null;
        }
    }


    public void exitDuke() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }


    // Calls the relevant function based on the given command
    // Return true if need to exit program
    public boolean executeCommand(String[] commands) {

        // The first token is used to identify which action to take
        switch (commands[0]) {

        case COMMAND_LIST:
            listTasks();
            return false;

        // Handle marking a task as done and undone in the same function
        case COMMAND_MARK_AS_DONE:
            // Fall through

        case COMMAND_MARK_AS_UNDONE:
            markTaskAsDoneOrUndone(commands);
            return false;

        
        // Handle adding all types of tasks in the same function
        case COMMAND_ADD_TODO:
            // Fall through
        case COMMAND_ADD_DEADLINE:
            // Fall through
        case COMMAND_ADD_EVENT:
            addTask(commands);
            return false;

        
        case COMMAND_EXIT:
            exitDuke();
            return true;
            
        default:
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
            String[] commands = parseCommand(sc.nextLine());

            // If need to exit
            if (executeCommand(commands)) {
                sc.close();
                return;
            }
        }
    }


    public String[] parseCommand(String command) {
        // Split string around whitespaces
        return command.split("\\s");
    }



    public static void main(String[] args) {
        
        Duke d = new Duke();

        // Print the logo
        d.initialize();

        // Process user commands
        d.processCommands();


    }
}
