package ren;

/**
 * Parser interprets user commands and executes them.
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Constructor for Parser.
     *
     * @param tasks The TaskList to execute commands on.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Interprets user commands and executes them.
     *
     * @param cmd Command to execute.
     * @return String containing a message for the user.
     * @throws RenException If command is invalid.
     */
    public String parseCommand(String cmd) throws RenException {
        String[] firstParse = cmd.split(" ", 2);
        String firstTerm = firstParse[0];
        boolean hasSecondTerm = firstParse.length > 1;

        switch (firstTerm) {
        case "bye":
            return " Farewell!\n";
        case "todo":
            if (hasSecondTerm) {
                return tasks.addTask(Ren.TaskType.TODO, firstParse[1], "");
            } else {
                throw new RenException("Please provide a description for the todo.");
            }
        case "deadline":
            if (hasSecondTerm) {
                String[] secondParse = firstParse[1].split("/by", 2);
                if (secondParse.length > 1) {
                    return tasks.addTask(Ren.TaskType.DEADLINE, secondParse[0], secondParse[1]);
                } else {
                    throw new RenException("Please provide a date/time for the deadline.");
                }
            } else {
                throw new RenException("Please provide a description for the deadline.");
            }
        case "event":
            if (hasSecondTerm) {
                String[] secondParse = firstParse[1].split("/at", 2);
                if (secondParse.length > 1) {
                    return tasks.addTask(Ren.TaskType.EVENT, secondParse[0], secondParse[1]);
                } else {
                    throw new RenException("Please provide a date/time for the event.");
                }
            } else {
                throw new RenException("Please provide a description for the event.");
            }
        case "delete":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                return tasks.deleteTask(Integer.parseInt(secondTerm));
            } catch (NumberFormatException e) {
                // If the second term parsed isn't an integer
                throw new RenException("Please indicate the task no. in digits.");
            } catch (RenException f) {
                // The user entered an invalid number
                return f.toString();
            }
        case "mark":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                return tasks.updateTask(true, Integer.parseInt(secondTerm));
            } catch (NumberFormatException e) {
                // If the second term parsed isn't an integer
                throw new RenException("Please indicate the task no. in digits.");
            } catch (RenException f) {
                // The user entered an invalid number
                return f.toString();
            }
        case "unmark":
            try {
                String secondTerm = hasSecondTerm
                    ? firstParse[1].split(" ", 2)[0]
                    : "0";
                return tasks.updateTask(false, Integer.parseInt(secondTerm));
            } catch (NumberFormatException e) {
                // If the second term parsed isn't an integer
                throw new RenException("Please indicate the task no. in digits.");
            } catch (RenException f) {
                // The user entered an invalid number
                return f.toString();
            }
        case "list":
            return tasks.listTasks();
        case "find":
            if (hasSecondTerm) {
                return tasks.findTasks(firstParse[1]);
            } else {
                throw new RenException("Please provide a search term.");
            }
        case "empty":
            return tasks.emptyList();
        default:
            throw new RenException("Please enter a supported command.");
        }
    }
}
