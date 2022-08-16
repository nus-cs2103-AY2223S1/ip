import java.util.Scanner;
import java.util.ArrayList;
import java.util.List; 

public class Duke {
    private Scanner scanner;
    private List<Task> list;

    private final String logo =   "        / \\     |_   _| | |  / _|                   | |\n"
                                + "       /   \\      | |   | | | |_   _ __    ___    __| |\n"
                                + "      / / \\ \\     | |   | | |  _| | '__|  / _ \\  / _` |\n"
                                + "     / _____ \\   _| |_  | | | |   | |    |  __/ | (_| |\n"
                                + "    /_/     \\_\\ |_____| |_| |_|   |_|     \\___|  \\__,_|\n";

    private final String greeting =   "____________________________________________________________\n" +
                                      "                      Hello! I am \n" + logo +
                                      "      Your personal assistant. What can I do for you?\n" +
                                      "____________________________________________________________";

    private final String goodbye = "Bye. Hope to see you again soon!";

    /**
     * Initializing the application 
     */
    public void initialize() throws DukeException{
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
    public void mark(String input) throws DukeException {
        int index = Integer.parseInt(input) - 1;
        try {
            list.get(index).markAsDone();
            System.out.println("____________________________________________________________\n" +
                    "Nice! I've marked this task as done: \n" +
                    list.get(index) + "\n" +
                    "____________________________________________________________\n");
        } catch (NullPointerException e) {
            throw new DukeException("☹ OOPS!!! There is no task created for this index!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index number!");
        }
    }

    /**
     * Unmarks a task in the list
     */
    public void unmark(String input) throws DukeException {
        int index = Integer.parseInt(input) - 1;

        try {
            list.get(index).markAsUndone();
            System.out.println("____________________________________________________________\n" +
                    "OK, I've marked this task as not done yet: \n" +
                    list.get(index) + "\n" +
                    "____________________________________________________________\n");
        } catch (NullPointerException e) {
            throw new DukeException("☹ OOPS!!! There is no task created for this index!");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index number!");
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
     * Deletes a task from the list
     */
    public void deleteTask(String input) throws DukeException {
        try {
            int i = Integer.parseInt(input.split(" ", 2)[0]);
            Task task = list.get(i);
            list.remove(i);
            int numOfTasks = list.size();

            System.out.println("____________________________________________________________\n" +
                    "Noted. I've removed this task: \n" +
                    task + "\n" +
                    "Now you have " + numOfTasks + " tasks in the list. \n" +
                    "____________________________________________________________\n");
        }  catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid index number!");
        }
    }

    /**
     * Handles a to do task
     * @param input String input for to do task
     * @throws DukeException Exception if to do task has no description
     */
    public void toDoTask(String[] input) throws DukeException {
        if (input.length >= 2) {
            Todo todo = new Todo(input[1]);
            addTask(todo);
        } else {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    /**
     * Handles an deadline task
     * @param input String input for deadline task
     * @throws DukeException Exception if deadline task has no description
     */
    public void deadlineTask(String[] input) throws DukeException {
        String[] deadlineDetails = input[1].split(" /by ", 2);

        if (deadlineDetails.length == 2) {
            Deadline deadline = new Deadline(deadlineDetails[0], deadlineDetails[1]);
            addTask(deadline);
        } else {
            throw new DukeException("☹ OOPS!!! Please follow the syntax for an 'deadline' command: event [description] /by [date].");
        }
    }

    /**
     * Handles an event task
     * @param input String input for event task
     * @throws DukeException Exception if event task has no description
     */
    public void eventTask(String[] input) throws DukeException {
        String[] eventDetails = input[1].split(" /at ", 2);

        if (eventDetails.length == 2) {
            Event event = new Event(eventDetails[0], eventDetails[1]);
            addTask(event);
        } else {
            throw new DukeException("☹ OOPS!!! Please follow the syntax for an 'event' command: event [description] /at [date].");
        }
    }

    /**
     * Listens to System.in for input
     */
    public void listen() throws DukeException {
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
                case ("delete"):
                    deleteTask(inputArr[1]);
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
                    toDoTask(inputArr);
                    break;
                case ("deadline"):
                    deadlineTask(inputArr);
                    break;
                case ("event"):
                    eventTask(inputArr);
                    break;
                default:
                    // Default case if there is no command word in the front
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        scanner.close();
    }
}
