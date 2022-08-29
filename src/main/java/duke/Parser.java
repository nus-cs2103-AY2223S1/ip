package duke;

/**
 * This class handles the parsing of the user input command.
 * This is the other half of the decision making tree.
 */
public class Parser {
    /** The TaskList where new tasks are added to. */
    private TaskList tasks;

    /**
     * The class constructor for Parser.
     *
     * @param tasks where tasks are to be added.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Handles all input and diverts it to the necessary methods for its
     * core functionality.
     *
     * @param input String from the user.
     * @return String of Duke's response.
     * @throws DukeException when the input is not known.
     */
    public String parse(String input) throws DukeException {
        String[] parts = input.split(" ");
        if (parts[0].equals("mark")) {
            return tasks.markTask(parts);
        } else if (parts[0].equals("todo")) {
            return tasks.createTodo(input);
        } else if (parts[0].equals("deadline")) {
            return tasks.createDeadline(input);
        } else if (parts[0].equals("event")) {
            return tasks.createEvent(input);
        } else if (parts[0].equals("delete")) {
            return tasks.deleteTask(parts);
        } else if (parts[0].equals("find")) {
            return tasks.findTasks(parts);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :(");
        }
    }
}

