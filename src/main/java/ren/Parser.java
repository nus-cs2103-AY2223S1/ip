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
        boolean hasSecondTerm = firstParse.length > 1;
        switch (firstParse[0]) {
        case "bye":
            return " Farewell!\n";
        case "todo":
            return parseTodo(hasSecondTerm, firstParse);
        case "deadline":
            return parseDeadline(hasSecondTerm, firstParse);
        case "event":
            return parseEvent(hasSecondTerm, firstParse);
        case "delete":
            return parseDelete(hasSecondTerm, firstParse);
        case "mark":
            return parseMark(hasSecondTerm, firstParse);
        case "unmark":
            return parseUnmark(hasSecondTerm, firstParse);
        case "list":
            return tasks.listTasks();
        case "find":
            return parseFind(hasSecondTerm, firstParse);
        case "empty":
            return tasks.emptyList();
        case "sort":
            return parseSort(hasSecondTerm, firstParse);
        default:
            throw new RenException("Please enter a supported command.");
        }
    }

    private String parseTodo(boolean hasSecondTerm, String[] firstParse) throws RenException {
        if (hasSecondTerm) {
            return tasks.addTask(Ren.TaskType.TODO, firstParse[1], "");
        } else {
            throw new RenException("Please provide a description for the todo.");
        }
    }

    private String parseDeadline(boolean hasSecondTerm, String[] firstParse) throws RenException {
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
    }

    private String parseEvent(boolean hasSecondTerm, String[] firstParse) throws RenException {
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
    }

    private String parseDelete(boolean hasSecondTerm, String[] firstParse) throws RenException {
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
    }

    private String parseMark(boolean hasSecondTerm, String[] firstParse) throws RenException {
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
    }

    private String parseUnmark(boolean hasSecondTerm, String[] firstParse) throws RenException {
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
    }

    private String parseFind(boolean hasSecondTerm, String[] firstParse) throws RenException {
        if (hasSecondTerm) {
            return tasks.findTasks(firstParse[1]);
        } else {
            throw new RenException("Please provide a search term.");
        }
    }

    private String parseSort(boolean hasSecondTerm, String[] firstParse) throws RenException {
        if (!hasSecondTerm) {
            throw new RenException("Please specify how you want the list to be sorted.");
        }
        String secondTerm = firstParse[1].split(" ", 2)[0];
        switch (secondTerm) {
        case "type":
            return tasks.sortTasks(TaskList.SortType.TYPE);
        case "status":
            return tasks.sortTasks(TaskList.SortType.STATUS);
        case "description":
            return tasks.sortTasks(TaskList.SortType.DESCRIPTION);
        case "date":
            return tasks.sortTasks(TaskList.SortType.DATE);
        default:
            throw new RenException("Please specify how you want the list to be sorted.");
        }
    }
}
