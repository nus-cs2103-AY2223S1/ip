import java.io.IOException;

/**
 * This class deals with making sense of the user command.
 *
 * @author Carrie Zheng Jiarui
 * @version CS2103T AY22/23 Semester 1, iP
 */
public class Parser {

    /**
     * A horizontal line to be displayed as separator for each activity with CaCa.
     */
    private static final String LINE = "____________________________________________________________\n";

    /**
     * A TaskList object containing all the tasks in a list.
     */
    private final TaskList tasks;

    /**
     * A Storage object to update tasks in the local file.
     */
    private final Storage storage;

    /**
     * Constructor for creating a parser to deal with user command.
     * @param tasks A list of tasks to be updated according to user command.
     * @param storage The storage system to update tasks in the local file.
     */
    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Indicates whether user command has stopped and ended the program.
     * @param input User input as command.
     * @return True if input has ended the program; false otherwise.
     * @throws IOException If there exists failed or interrupted I/O operations.
     */
    public boolean hasStopped(String input) throws IOException {
        // Detect user command, where 1st element is the type of action to be done (command type),
        // 2nd element is the task description, with or without date/time.
        String[] command = input.split(" ", 2);
        String commandType = command[0];

        // Prints a line after a user input, to start CaCa response.
        System.out.print(LINE);

        try {
            if (commandType.isBlank()) {
                throw new EmptyInputException("OOPS!!! You have entered an empty input.");

            } else if (commandType.equals("bye")) {
                Ui.bye();
                return false;

            } else if (commandType.equals("todo")) {
                // Checks for valid description, i.e. not empty or blank, before adding todo.
                TaskList.hasDescription(command);

                TaskList.addToDo(command[1]);

                storage.updateFile(tasks);

            } else if (commandType.equals("deadline")) {
                // Checks for valid description, i.e. not empty or blank, before adding deadline.
                TaskList.hasDescription(command);

                TaskList.addDeadline(command[1]);

                storage.updateFile(tasks);

            } else if (commandType.equals("event")) {
                // Checks for valid description, i.e. not empty or blank, before adding event.
                TaskList.hasDescription(command);

                TaskList.addEvent(command[1]);

                storage.updateFile(tasks);

            } else if (commandType.equals("list")) {
                TaskList.listTasks();

            } else if (commandType.equals("mark")) {
                TaskList.markTask(command[1]);

                storage.updateFile(tasks);

            } else if (commandType.equals("unmark")) {
                TaskList.unmarkTask(command[1]);

                storage.updateFile(tasks);

            } else if (commandType.equals("delete")) {
                TaskList.deleteTask(command[1]);

                storage.updateFile(tasks);

            } else {
                // Invalid input.
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");

            }

        } catch (CaCaException e) {
            System.out.println(e.getMessage());
            System.out.println("Please try again!");

        } finally {
            // Prints a line to end CaCa response.
            System.out.println(LINE);
        }
        return true;
    }

}
