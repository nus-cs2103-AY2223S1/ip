package duke;

import java.util.Set;

public class Parser {
    private static final Set<String> SET_OF_COMMANDS = Set.of(
        "bye", "list", "mark", "unmark", "delete", "find", "todo", "deadline", "event");

    /**
     * Parses input by user and returns Command object if user input is valid
     *
     * @param fullCommand String command input by user
     * @return Command object to execute command input by user
     * @throws DukeException If user input is invalid (no such command, invalid description)
     */
    public static Command parse(String fullCommand) throws DukeException {
        // split into ["userCommand", "description"]
        String[] userInput = fullCommand.split(" ", 2);
        String userCommand = userInput[0].toLowerCase();
        Command command;

        if (!SET_OF_COMMANDS.contains(userCommand)) {
            throw new DukeException("I'm sorry, but I don't know what that means.");
        }

        switch (userCommand) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "mark":
            command = new MarkCommand(parseIndex(getDescription(userInput)));
            break;
        case "unmark":
            command = new UnmarkCommand(parseIndex(getDescription(userInput)));
            break;
        case "delete":
            command = new DeleteCommand(parseIndex(getDescription(userInput)));
            break;
        case "find":
            command = new FindCommand(getDescription(userInput));
            break;
        default:
            String taskDescription = getDescription(userInput);
            Task task = createTask(userCommand, taskDescription);
            command = new AddCommand(task);
            break;
        }
        return command;
    }

    /**
     * Parses 1 line from file containing saved data and creates Task object
     *
     * @param fullCommand Line from file containing saved data
     * @return Task object containing details stated in line parsed
     */
    public static Task parseFromFile(String fullCommand) {
        // split into ["userCommand", "isDone", "description"]
        String[] userInput = fullCommand.split(" ", 3);
        String userCommand = userInput[0].toLowerCase();
        boolean isDone = Boolean.parseBoolean(userInput[1]);

        return createTask(userCommand, userInput[2], isDone);
    }

    /**
     * Checks if user has input description for the task and returns the description
     *
     * @param userInput String array after splitting input String by user
     * @return Description of task
     * @throws DukeException If description is empty
     */
    private static String getDescription(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException(
                String.format("The description of %s cannot be empty.", userInput[0]));
        }

        return userInput[1];
    }

    /**
     * Checks if user has input a number and returns the number as an int
     *
     * @param description String format of an index input by user
     * @return int format of the index
     * @throws DukeException If index is not a number
     */
    private static int parseIndex(String description) throws DukeException {
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number!");
        }
    }

    /**
     * Creates Task object with given taskName and taskDescription, marked as not done
     *
     * @param taskName        Name of the Task to be created
     * @param taskDescription Description of the Task to be created
     * @return Task created
     */
    private static Task createTask(String taskName, String taskDescription) {
        return createTask(taskName, taskDescription, false);
    }

    /**
     * Creates Task object with given taskName and taskDescription and isDone
     *
     * @param taskName        Name of the Task to be created
     * @param taskDescription Description of the Task to be created
     * @param isDone          boolean indicating if the task is marked as done
     * @return Task created
     */
    private static Task createTask(String taskName, String taskDescription, boolean isDone) {
        String[] temp = taskDescription.split(" /", 2);

        switch (taskName) {
        case "todo":
            return new Todo(taskDescription, isDone);
        case "event":
            return new Event(temp[0], temp[1].split(" ", 2)[1], isDone);
        case "deadline":
            return new Deadline(temp[0], temp[1].split(" ", 2)[1], isDone);
        default:
            // should not reach this part
            return null;
        }
    }
}
