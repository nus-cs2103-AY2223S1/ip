package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * An abstraction for a command to Duke.
 */
public enum Command {
    DELETE("delete"),
    FIND("find"),
    LIST("list"),
    DEADLINE("deadline"),
    EVENT("event"),
    TODO("todo"),
    MARK("mark"),
    UNMARK("unmark");

    private static final String INPUT_DATE_FORMAT = "dd-MM-yyyy";

    private final String name;
    Command(String name) {
        this.name = name;
    }

    /**
     * Runs the command with the given Duke bot.
     *
     * @param args Command arguments.
     * @param duke Duke bot executing the command.
     */
    public String run(String args, Duke duke) {
        TaskList taskList = duke.getTaskList();
        Storage storage = duke.getStorage();
        String errorMessage = getUsageError(args, duke);
        if (!errorMessage.isEmpty()) {
            return errorMessage;
        }

        switch(name) {
        case "delete":
            int index = Integer.parseInt(args) - 1;
            Task t = taskList.deleteTask(index);
            try {
                storage.rewriteFile(taskList.getTasks());
            } catch (IOException e) {
                return "Unable to write to file.";
            }
            return "Noted. I've removed this task:\n" + "  " + t
                    + "\nNow you have " + taskList.getCount() + " tasks in the list.";

        case "find":
            if (args.charAt(0) == '"' && args.charAt(args.length() - 1) == '"') {
                args = args.substring(1, args.length() - 1);
            }
            ArrayList<Task> matchingTasks = taskList.getMatchingTasks(args);
            if (matchingTasks.isEmpty()) {
                return "No results found for keyword '" + args + "'";
            } else {
                return "Here are the matching tasks in your list:\n" + listTasks(matchingTasks);
            }

        case "list":
            return "Here are the tasks in your list:\n" + listTasks(taskList.getTasks());

        case "deadline":
            String[] temp = args.split(" /by ", 2);
            t = new Deadline(temp[0], LocalDate.parse(temp[1],
                    DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));
            taskList.addTask(t);
            try {
                storage.appendTaskToFile(t);
            } catch (IOException e) {
                return "Unable to write to file.";
            }
            return "Got it. I've added this task:\n" + "  " + t + "\nNow you have "
                    + taskList.getCount() + " tasks in the list.";

        case "event":
            temp = args.split(" /at ", 2);
            t = new Event(temp[0], LocalDate.parse(temp[1],
                    DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT)));
            taskList.addTask(t);
            try {
                storage.appendTaskToFile(t);
            } catch (IOException e) {
                return "Unable to write to file.";
            }
            return "Got it. I've added this task:\n" + "  " + t + "\nNow you have "
                    + taskList.getCount() + " tasks in the list.";

        case "todo":
            t = new ToDo(args);
            taskList.addTask(t);
            try {
                storage.appendTaskToFile(t);
            } catch (IOException e) {
                return "Unable to write to file.";
            }
            return "Got it. I've added this task:\n" + "  " + t + "\nNow you have "
                    + taskList.getCount() + " tasks in the list.";

        case "mark":
            // Fallthrough

        case "unmark":
            index = Integer.parseInt(args) - 1;
            t = taskList.getTasks().get(index);
            boolean b = name.equals("mark");
            taskList.markTask(t, b);
            try {
                storage.rewriteFile(taskList.getTasks());
            } catch (IOException e) {
                return "Unable to write to file.";
            }

            if (b) {
                return "Nice! I've marked this task as done: \n"
                        + "  " + t;
            } else {
                return "OK, I've marked this task as not done: \n"
                        + "  " + t;
            }

        default:
            return "";
        }
    }

    private boolean isValidDate(String date) {
        try {
            LocalDate d = LocalDate.parse(date, DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private String listTasks(ArrayList<Task> tasks) {
        String ret = "";
        for (int i = 0; i < tasks.size(); ++i) {
            ret += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return ret;
    }

    private String getUsageError(String args, Duke duke) {
        TaskList taskList = duke.getTaskList();
        String usage = getCorrectUsage();
        switch(name) {
        case "delete":
            if (args.isEmpty()) {
                return "Please specify a task number.\n\n" + usage;
            }
            return "";

        case "find":
            if (args.isBlank()) {
                return "Please specify at least one keyword.\n\n" + usage;
            } else if (args.charAt(0) != '"' || args.charAt(args.length() - 1) != '"') {
                if (args.split(" ").length > 1) {
                    return "'find' only expects 1 argument.\n\n If there are multiple "
                            + "keywords, please enclose them in quotation marks (\"\").";
                }
            } else {
                if (args.length() == 2 || args.substring(1, args.length() - 1).isBlank()) {
                    return "Please specify at least one keyword.\n\n" + usage;
                }
            }
            return "";

        case "list":
            if (!args.isEmpty()) {
                return "'list' expects no arguments.";
            }
            return "";

        case "deadline":
            String[] temp = args.split(" /by ", 2);
            if (temp.length < 2) {
                return "Please specify a task and deadline.\n\n" + usage;
            } else if (!isValidDate(temp[1])) {
                return "Please specify the due date in the right format.\n\n" + usage;
            }
            return "";

        case "event":
            temp = args.split(" /at ", 2);
            if (temp.length < 2) {
                return "Please specify an event and date.\n\n" + usage;
            } else if (!isValidDate(temp[1])) {
                return "Please specify the event date in the right format.\n\n" + usage;
            }
            return "";

        case "todo":
            if (args.isEmpty()) {
                return "Please specify a task.\n\n" + usage;
            }
            return "";

        case "mark":
            // Fallthrough

        case "unmark":
            if (args.isEmpty()) {
                return "Please specify a task number\n\n" + usage;
            } else {
                try {
                    int index = Integer.parseInt(args) - 1;
                    if (index < 0 || index >= taskList.getCount()) {
                        return "Please specify a valid task number.\n"
                                + "There are " + taskList.getCount()
                                + " task(s) in the list.\n\n" + usage;
                    }
                } catch (NumberFormatException e) {
                    return "Please specify a task number.\n\n"
                            + "\"" + args + "\"" + " is not an item number\n" + usage;
                }
            }
            return "";

        default:
            return "";
        }
    }

    private String getCorrectUsage() {
        String ret = "Correct usage: ";
        switch(name) {
        case "delete":
            return ret + "delete [task-number]";

        case "find":
            return ret + "find [keyword(s)]";

        case "deadline":
            return ret + "deadline [task-description] /by [DD-MM-YYYY]";

        case "event":
            return ret + "event [task-description] /at [DD-MM-YYYY]";

        case "todo":
            return ret + "todo [task-description]";

        case "mark":
            return ret + "mark [task-number]";

        case "unmark":
            return ret + "unmark [task-number]";

        default:
            return "";
        }
    }
}
