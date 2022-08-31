package Duke;

/**
 * A class that handles user input.
 */
public class Parser {
    /**
     * Parses an input string and calls the relevant method (if any).
     *
     * @param input The input string to be parsed.
     */
    public static boolean parseInput(String input) throws DukeException {
        String[] words = input.toLowerCase().split(" ", 2);
        String command = words[0];
        String args = "";
        if (words.length > 1) {
            args = words[1];
        }

        switch (command) {
        case "hello":
            Ui.displayGreeting();
            break;
        case "bye":
            return false;
        case "list":
            Ui.displayTasks();
            break;
        case "mark":
        case "unmark":
            boolean isDone = command.equals("mark");
            Ui.displayMarkTaskMessage(TaskList.markTask(isDone, args), isDone);
            break;
        case "todo":
            Ui.displayAddTaskMessage(TaskList.addToDo(args));
            break;
        case "deadline":
            Ui.displayAddTaskMessage(TaskList.addDeadline(args));
            break;
        case "event":
            Ui.displayAddTaskMessage(TaskList.addEvent(args));
            break;
        case "delete":
            Ui.displayDeleteTaskMessage(TaskList.deleteTask(args));
            break;
        default:
            throw new DukeException("Command not recognised");
        }

        return true;
    }
}
