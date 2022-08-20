/**
 * Parser interprets user commands and executes them.
 */
public class Parser {
    /**
     * Interprets user commands ad executes them.
     *
     * @param cmd Command to execute.
     * @param tasks TaskList to execute commands on.
     * @return String containing a message for the user.
     * @throws DukeException If command is invalid.
     */
    public static String parseCommand(String cmd, TaskList tasks) throws DukeException {
        String[] firstParse = cmd.split(" ", 2);
        String firstTerm = firstParse[0];
        boolean hasSecondTerm = firstParse.length > 1;

        switch (firstTerm) {
        case "bye":
            return Ui.goodbye();
        case "todo":
            if (hasSecondTerm) {
                return tasks.addTask(Duke.TaskType.TODO, firstParse[1], "");
            } else {
                throw new DukeException("Please provide a description for the todo.");
            }
        case "deadline":
            if (hasSecondTerm) {
                String[] secondParse = firstParse[1].split("/by", 2);
                if (secondParse.length > 1) {
                    return tasks.addTask(Duke.TaskType.DEADLINE, secondParse[0], secondParse[1]);
                } else {
                    throw new DukeException("Please provide a date/time for the deadline.");
                }
            } else {
                throw new DukeException("Please provide a description for the deadline.");
            }
        case "event":
            if (hasSecondTerm) {
                String[] secondParse = firstParse[1].split("/at", 2);
                if (secondParse.length > 1) {
                    return tasks.addTask(Duke.TaskType.EVENT, secondParse[0], secondParse[1]);
                } else {
                    throw new DukeException("Please provide a date/time for the event.");
                }
            } else {
                throw new DukeException("Please provide a description for the event.");
            }
        case "delete":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                return tasks.deleteTask(Integer.parseInt(secondTerm));
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate the task no. in digits.");
            } catch (DukeException f) {
                return f.toString();
            }
        case "mark":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                return tasks.updateTask(true, Integer.parseInt(secondTerm));
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate the task no. in digits.");
            } catch (DukeException f) {
                return f.toString();
            }
        case "unmark":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                return tasks.updateTask(false, Integer.parseInt(secondTerm));
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate the task no. in digits.");
            } catch (DukeException f) {
                return f.toString();
            }
        case "list":
            return tasks.listTasks();
        case "empty":
            return tasks.emptyList();
        default:
            throw new DukeException("Please enter a supported command.");
        }
    }
}
