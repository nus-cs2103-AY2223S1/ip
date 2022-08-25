package duke;

import java.util.Set;

// TODO 1: Parse the commands
public class Parser {
    private static final Set<String> SET_OF_COMMANDS = Set.of(
            "bye", "list", "mark", "unmark", "delete", "find", "todo", "deadline", "event");

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

    public static Task parseFromFile(String fullCommand) {
        // split into ["userCommand", "isDone", "description"]
        String[] userInput = fullCommand.split(" ", 3);
        String userCommand = userInput[0].toLowerCase();
        boolean isDone = Boolean.parseBoolean(userInput[1]);

        return createTask(userCommand, userInput[2], isDone);
    }

    private static String getDescription(String[] userInput) throws DukeException {
        if (userInput.length < 2) {
            throw new DukeException(
                String.format("The description of %s cannot be empty.", userInput[0]));
        }

        return userInput[1];
    }

    private static int parseIndex(String description) throws DukeException {
        try {
            return Integer.parseInt(description);
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a number!");
        }
    }

    private static Task createTask(String taskName, String taskDescription) {
        return createTask(taskName, taskDescription, false);
    }

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
