import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Duke {
    private static ArrayList<Task> inputs = new ArrayList<>();
//  private static Storage storage = new Storage("./data/duke.txt");
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor for a Duke instance.
     *
     * @param filePath the path to the file for data storage
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.taskList = new TaskList(this.storage.getTasks(), this.ui, this.storage);
        this.ui.updateTaskList(this.taskList);
    }

    /**
     * The main application.
     */
    public void run() {
        this.ui.printGreeting();

        boolean exit = false;
        while (!exit) {
            try {
                exit = this.ui.handleInput();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }


    /**
     * The main program loop.
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }

//    /**
//     * Gets the command word from the given input line.
//     *
//     * @param inputText the user's input in the console
//     * @param firstSpaceIdx the index of the space following the command word
//     *
//     * @return The current command
//     *
//     * @throws DukeException if the input doesn't have a valid command word
//     */
//    public static Command getCommand(String inputText,
//                                     int firstSpaceIdx) throws DukeException {
//        String commandName = firstSpaceIdx == -1
//                             ? inputText
//                             : inputText.substring(0, firstSpaceIdx);
//        Command result = Command.get(commandName);
//
//        // Check if command is valid
//        if (result == null) {
//            throw new DukeException("I'm sorry, but I don't know what that means :-(");
//        }
//
//        return result;
//    }

//    /**
//     * Add a Deadline to the items list.
//     *
//     * @param input A String to be added to the list.
//     *
//     * @throws DukeException if either the desc or the by param is empty
//     */
//    public static void addDeadline(String input) throws DukeException {
//        // Get description and date of deadline
//        int slashIdx = input.indexOf("/by");
//
//        // If there is no /by, throw an error
//        if (slashIdx == -1 || slashIdx > input.length() - 5) {
//            throw new DukeException("Please set a due date!");
//        }
//
//        String desc = input.substring(0, slashIdx);
//        String by = input.substring((slashIdx + 4));
//
//        if (desc.length() == 0) {
//            throw new DukeException("The description of a deadline cannot be empty.");
//        }
//
//        // Instantiate task object
//        Task newDeadline = new Deadline(desc, by);
//
//        // Add to list
//        Duke.inputs.add(newDeadline);
//
//        // Print message
//        printAddedTask(newDeadline);
//
//        // Update data file
//        Duke.storage.saveTasks(Duke.inputs);
//    }

//    /**
//     * Add a To-do to the items list.
//     *
//     * @param input A String to be added to the list.
//     *
//     * @throws DukeException if the description is empty
//     */
//    public static void addTodo(String input) throws DukeException {
//        // Check if input is empty
//        if (input.length() == 0) {
//            throw new DukeException("The description of a todo cannot be empty.");
//        }
//
//        // Instantiate task object
//        Task newDeadline = new Todo(input);
//
//        // Add to list
//        Duke.inputs.add(newDeadline);
//
//        // Print message
//        printAddedTask(newDeadline);
//
//        // Update data file
//        Duke.storage.saveTasks(Duke.inputs);
//    }

//    /**
//     * Add a Deadline to the items list.
//     *
//     * @param input A String to be added to the list.
//     *
//     * @throws DukeException if description or date is empty
//     */
//    public static void addEvent(String input) throws DukeException {
//        // Get description and date of event
//        int slashIdx = input.indexOf("/at");
//
//        // If there is no /at, throw an error
//        if (slashIdx == -1 || slashIdx > input.length() - 5) {
//            throw new DukeException("Please set a date!");
//        }
//
//        String desc = input.substring(0, slashIdx);
//        String at = input.substring((slashIdx + 4));
//
//        if (desc.length() == 0) {
//            throw new DukeException("The description of an event cannot be empty.");
//        }
//
//        // Instantiate task object
//        Task newDeadline = new Event(desc, at);
//
//        // Add to list
//        Duke.inputs.add(newDeadline);
//
//        // Print message
//        printAddedTask(newDeadline);
//
//        // Update data file
//        Duke.storage.saveTasks(Duke.inputs);
//    }



//    /**
//     * Marks a task at a certain index as done.
//     *
//     * @param index the index of the task to be marked as done
//     *
//     * @throws DukeException when an invalid index is provided
//     */
//    public static void mark(int index) throws DukeException {
//        // Check if the index is within the bounds of the list
//        if (index <= 0 || index > Duke.inputs.size()) {
//            throw new DukeException("Invalid index");
//        }
//
//        // Get the task to be marked
//        Task selectedTask = Duke.inputs.get(index - 1);
//
//        // Mark it as done
//        selectedTask.mark();
//
//        // Print message
//        System.out.println("Nice! I've marked this task as done:\n"+
//                           selectedTask);
//
//        // Update data file
//        Duke.storage.saveTasks(Duke.inputs);
//    }

//    /**
//     * Marks a task at a certain index as done.
//     *
//     * @param index the index of the task to be marked as done
//     *
//     * @throws DukeException when an invalid index is provided
//     */
//    public static void unmark(int index) throws DukeException {
//        // Check if the index is within the bounds of the list
//        if (index <= 0 || index > Duke.inputs.size()) {
//            throw new DukeException("Invalid index");
//        }
//
//        // Get the task to be marked
//        Task selectedTask = Duke.inputs.get(index - 1);
//
//        // Mark it as not done
//        selectedTask.unmark();
//
//        // Print message
//        System.out.println("OK, I've marked this task as not done yet:\n"+
//                           selectedTask);
//
//        // Update data file
//        Duke.storage.saveTasks(Duke.inputs);
//    }


//    public static void delete(int index) throws DukeException {
//        // Check if the index is within the bounds of the list
//        if (index <= 0 || index > Duke.inputs.size()) {
//            throw new DukeException("Invalid index");
//        }
//
//        // Remove task from list and get removed task
//        Task removedTask = Duke.inputs.remove(index - 1);
//
//        // Print message
//        System.out.println("Noted. I've removed this task:");
//        System.out.println(removedTask);
//        System.out.println(String.format("Now you have %d tasks in the list.",
//                                         inputs.size()));
//
//        // Update data file
//        Duke.storage.saveTasks(Duke.inputs);
//    }

//    public static void printAllOnDate(String input) {
//        LocalDate date = LocalDate.parse(input);
//        for (Task task : Duke.inputs) {
//            // Check that task is not a To-do which has no deadline
//            if (task instanceof Todo) {
//                continue;
//            }
//
//            // If the deadline is equal to the date, print it
//            if (task.getDeadline().equals(date)) {
//                System.out.println(task);
//            }
//        }
//    }
}
