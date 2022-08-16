import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 

public class Duke {
    private Scanner scanner;
    private List<Task> list;

    private String logo =     "        / \\     |_   _| | |  / _|                   | |\n"
                            + "       /   \\      | |   | | | |_   _ __    ___    __| |\n"
                            + "      / / \\ \\     | |   | | |  _| | '__|  / _ \\  / _` |\n"
                            + "     / _____ \\   _| |_  | | | |   | |    |  __/ | (_| |\n"
                            + "    /_/     \\_\\ |_____| |_| |_|   |_|     \\___|  \\__,_|\n";

    private String greeting = "____________________________________________________________\n" +
                              "                      Hello! I am \n" + logo +
                              "      Your personal assistant. What can I do for you?\n" +
                              "____________________________________________________________";

    private String goodbye = "Bye. Hope to see you again soon!";

    /**
     * Initializing the application 
     */
    public void initialize() {
        list = new ArrayList<>(100);
        scanner = new Scanner(System.in);
        System.out.println(greeting);

        listen();
    }

    /**
     * Prints all values of list currently
     */
    public void printList() {
        System.out.println("____________________________________________________________\n");
        
        // Empty list
        if (list.size() == 0) {
            System.out.print("List is currently empty! \n");
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println((i+1) + ". " +list.get(i) + "\n");
        }

        System.out.println("____________________________________________________________\n");
    }

    /**
     * Marks a task in the list
     */
    public void mark(String input) {
        int index = Integer.parseInt(input) - 1;

        if (index + 1 > list.size() || index + 1 <= 0) {
            System.out.println("____________________________________________________________\n" +
                               "There is no task created for index " + (index + 1) + "\n" +
                               "____________________________________________________________\n");
        } else {
            list.get(index).markAsDone();

            System.out.println("____________________________________________________________\n" +
                            "Nice! I've marked this task as done: \n" +
                            list.get(index) + "\n" + 
                            "____________________________________________________________\n");
        }
    }

    /**
     * Unmarks a task in the list
     */
    public void unmark(String input) {
        int index = Integer.parseInt(input) - 1;

        if (index + 1 > list.size() || index + 1 <= 0) {
            System.out.println("____________________________________________________________\n" +
                               "There is no task created for index " + (index + 1) + "\n" +
                               "____________________________________________________________\n");
        } else {
            list.get(index).markAsUndone();

            System.out.println("____________________________________________________________\n" +
                            "OK, I've marked this task as not done yet: \n" +
                            list.get(index) + "\n" + 
                            "____________________________________________________________\n");
        }
    }

    /**
     * Adds a task to the list
     */
    public void addTask(Task task) {
        list.add(task);
        int numOfTasks = list.size();

        System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task: \n" +
                            task + "\n" +
                            "Now you have " + numOfTasks + " tasks in the list. \n" + 
                            "____________________________________________________________\n");
    }

    /**
     * Listens to System.in for input
     */
    public void listen() {
        String input; // initializing the input

        // Reading user inputs
        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            String[] inputArr = input.split(" ", 2); // Splits the string in an array of [command, others]
            String command = inputArr[0];

            switch (command) {
                case ("bye"):
                    System.out.println(goodbye);
                    System.exit(0);
                    break;
                case ("list"):
                    printList();
                    break;
                case ("mark"):
                    // First word is "mark", second word would be an index for task to be marked
                    mark(inputArr[1]);
                    break;
                case ("unmark"):
                    // First word is "unmark", second word would be an index for task to be unmarked
                    unmark(inputArr[1]);
                    break;
                case ("todo"):
                    Todo todo = new Todo(inputArr[1]);
                    addTask(todo);
                    break;
                case ("deadline"):
                    String[] deadlineDetails = inputArr[1].split(" /by ", 2);

                    if (deadlineDetails.length == 2) {
                        Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        addTask(deadline);
                    } else {
                        System.out.println("Please provide a proper command");
                    }
                    break;
                case ("event"):
                    String[] eventDetails = inputArr[1].split(" /at ", 2);

                    if (eventDetails.length == 2) {
                        Event event = new Event(eventDetails[0], eventDetails[1]);
                        addTask(event);
                    } else {
                        System.out.println("Please provide a proper command");
                    }
                    break;
                default:
                    // Default case if there is no command word in the front
                    Task task = new Task(input);
                    addTask(task);
            }
        }
        scanner.close();
    }
}
