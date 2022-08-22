import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> inputs = new ArrayList<>();
    private static FileHandler fileHandler = new FileHandler();

    /**
     * The main program loop.
     *
     * @param args
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n"
                           + logo
                           + "\nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        Duke.inputs = Duke.fileHandler.getTasks();

        boolean flag = true; // flag indicating if the loop should continue
        while (flag && sc.hasNextLine()) {
            // Read input from console
            String inputText = sc.nextLine();
            try {
                // Get first word
                int firstSpaceIdx = inputText.indexOf(" ");
                Command command = Duke.getCommand(inputText, firstSpaceIdx);

                // Determine the action to perform
                switch (command) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        flag = false;
                        break;
                    case LIST:
                        Duke.printItems();
                        break;
                    case MARK:
                        int markindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
                        Duke.mark(markindex);
                        break;
                    case UNMARK:
                        int unmarkindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
                        Duke.unmark(unmarkindex);
                        break;
                    case DELETE:
                        int deleteindex = Integer.parseInt(inputText.substring(firstSpaceIdx + 1));
                        Duke.delete(deleteindex);
                        break;
                    case DEADLINE:
                        String deadlineInfo = inputText.substring(firstSpaceIdx + 1);
                        Duke.addDeadline(deadlineInfo);
                        break;
                    case TODO:
                        String todoInfo = inputText.substring(firstSpaceIdx + 1);
                        Duke.addTodo(todoInfo);
                        break;
                    case EVENT:
                        String eventInfo = inputText.substring(firstSpaceIdx + 1);
                        Duke.addEvent(eventInfo);
                        break;
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            }
        }
    }

    /**
     * Gets the command word from the given input line.
     *
     * @param inputText the user's input in the console
     * @param firstSpaceIdx the index of the space following the command word
     *
     * @return The current command
     *
     * @throws DukeException if the input doesn't have a valid command word
     */
    public static Command getCommand(String inputText,
                                     int firstSpaceIdx) throws DukeException {
        String commandName = firstSpaceIdx == -1
                             ? inputText
                             : inputText.substring(0, firstSpaceIdx);
        Command result = Command.get(commandName);

        // Check if command is valid
        if (result == null) {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return result;
    }

    /**
     * Add a Deadline to the items list.
     *
     * @param input A String to be added to the list.
     *
     * @throws DukeException if either the desc or the by param is empty
     */
    public static void addDeadline(String input) throws DukeException {
        // Get description and date of deadline
        int slashIdx = input.indexOf("/by");

        // If there is no /by, throw an error
        if (slashIdx == -1 || slashIdx > input.length() - 5) {
            throw new DukeException("Please set a due date!");
        }

        String desc = input.substring(0, slashIdx);
        String by = input.substring((slashIdx + 4));

        if (desc.length() == 0) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        // Instantiate task object
        Task newDeadline = new Deadline(desc, by);

        // Add to list
        Duke.inputs.add(newDeadline);

        // Print message
        printAddedTask(newDeadline);

        // Update data file
        Duke.fileHandler.saveTasks(Duke.inputs);
    }

    /**
     * Add a To-do to the items list.
     *
     * @param input A String to be added to the list.
     *
     * @throws DukeException if the description is empty
     */
    public static void addTodo(String input) throws DukeException {
        // Check if input is empty
        if (input.length() == 0) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        // Instantiate task object
        Task newDeadline = new Todo(input);

        // Add to list
        Duke.inputs.add(newDeadline);

        // Print message
        printAddedTask(newDeadline);

        // Update data file
        Duke.fileHandler.saveTasks(Duke.inputs);
    }

    /**
     * Add a Deadline to the items list.
     *
     * @param input A String to be added to the list.
     *
     * @throws DukeException if description or date is empty
     */
    public static void addEvent(String input) throws DukeException {
        // Get description and date of event
        int slashIdx = input.indexOf("/at");

        // If there is no /at, throw an error
        if (slashIdx == -1 || slashIdx > input.length() - 5) {
            throw new DukeException("Please set a date!");
        }

        String desc = input.substring(0, slashIdx);
        String at = input.substring((slashIdx + 4));

        if (desc.length() == 0) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        // Instantiate task object
        Task newDeadline = new Event(desc, at);

        // Add to list
        Duke.inputs.add(newDeadline);

        // Print message
        printAddedTask(newDeadline);

        // Update data file
        Duke.fileHandler.saveTasks(Duke.inputs);
    }

    /**
     * Prints the feedback when a new task has been added.
     *
     * @param newTask The recently added task
     */
    public static void printAddedTask(Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println(String.format("Now you have %d tasks in the list.",
                                         inputs.size()));
    }

    /**
     * Prints all items in the items list.
     */
    public static void printItems() {
        System.out.println("Here are the tasks in your list:");

        // Print every task in the list
        for (int i = 0; i < Duke.inputs.size(); i++) {
            String output = String.format("%s. %s", i + 1, Duke.inputs.get(i));
            System.out.println(output);
        }

    }

    /**
     * Marks a task at a certain index as done.
     *
     * @param index the index of the task to be marked as done
     *
     * @throws DukeException when an invalid index is provided
     */
    public static void mark(int index) throws DukeException {
        // Check if the index is within the bounds of the list
        if (index <= 0 || index > Duke.inputs.size()) {
            throw new DukeException("Invalid index");
        }

        // Get the task to be marked
        Task selectedTask = Duke.inputs.get(index - 1);

        // Mark it as done
        selectedTask.mark();

        // Print message
        System.out.println("Nice! I've marked this task as done:\n"+
                           selectedTask);

        // Update data file
        Duke.fileHandler.saveTasks(Duke.inputs);
    }

    /**
     * Marks a task at a certain index as done.
     *
     * @param index the index of the task to be marked as done
     *
     * @throws DukeException when an invalid index is provided
     */
    public static void unmark(int index) throws DukeException {
        // Check if the index is within the bounds of the list
        if (index <= 0 || index > Duke.inputs.size()) {
            throw new DukeException("Invalid index");
        }

        // Get the task to be marked
        Task selectedTask = Duke.inputs.get(index - 1);

        // Mark it as not done
        selectedTask.unmark();

        // Print message
        System.out.println("OK, I've marked this task as not done yet:\n"+
                           selectedTask);

        // Update data file
        Duke.fileHandler.saveTasks(Duke.inputs);
    }


    public static void delete(int index) throws DukeException {
        // Check if the index is within the bounds of the list
        if (index <= 0 || index > Duke.inputs.size()) {
            throw new DukeException("Invalid index");
        }

        // Remove task from list and get removed task
        Task removedTask = Duke.inputs.remove(index - 1);

        // Print message
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println(String.format("Now you have %d tasks in the list.",
                                         inputs.size()));

        // Update data file
        Duke.fileHandler.saveTasks(Duke.inputs);
    }
}
