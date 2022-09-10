package duke.util;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Recurring;
import duke.task.Task;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * For anything related to printing statements in Duke.
 */
public class Ui {
    public static final Map<String, String> COMMAND_HELP = Stream.of(new String[][] {
            { "todo", "todo [description]" },
            { "deadline", "deadline [description] /by [dd/MM/yy] <24hr time>" },
            { "event", "event [description] /at [dd/MM/yy] <24hr time>" },
            { "recurring", "recurring [description] /every [d/M or d or day or time] </at 24hr time> *[times]" },
            { "find", "find [String]" },
            { "mark", "mark [index]" },
            { "unmark", "unmark [index]" },
            { "delete", "delete [index]" },
            { "remaining", "remaining [index]" },
            { "list", "list" },
            { "bye", "bye" },
    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static String showHello() {
        return "Hello! What plans do you currently have?" + "\nUse /? for help";
    }

    public static String showBye() {
        return "Bye! Hope that I was of service!";
    }

    public static String showHelp() {
        StringBuilder str = new StringBuilder();
        str.append("These are the commands available:\n");
        for (String desc : COMMAND_HELP.values()) {
            str.append("\n\t").append(desc);
        }
        str.append("\n");
        return str.toString();
    }

    public static String displayList(TaskList list) {
        if (list.getSize() == 0) {
            return Ui.getListSize(list);
        }
        StringBuilder str = new StringBuilder();
        str.append("Here are your plans:");
        int number = 1;
        for (Task task : list.getArray()) {
            str.append("\n\t").append(number).append(".").append(task);
            number++;
        }
        str.append("\n");
        str.append(Ui.getListSize(list));
        str.append("Pls don't procrastinate on the above tasks!");
        return str.toString();
    }

    public static String markTaskDone(Task task) {
        return "nice! I've marked this task as done:\n" + "\t" + task + "\n";
    }

    public static String markTaskNotDone(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("Ok! I've marked this task as not done yet:\n");
        str.append("\t").append(task).append("\n");
        return str.toString();
    }

    public static String deleteTask(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("Ok! I've deleted this task:\n");
        str.append("\t").append(task).append("\n");
        return str.toString();
    }

    public static String addTask(Task task) {
        return "added: " + task + "\n";
    }

    public static String getListSize(TaskList list) {
        return "You currently have " + list.getSize() + " tasks in the list\n";
    }

    public static String showRemaining(Recurring task) {
        StringBuilder str = new StringBuilder("Dates for the task: ");
        str.append(task.getDescription()).append("\n");
        str.append(task.showDates());
        return str.toString();
    }

    public static String findKeyword(String keyword, ArrayList<Task> list) {
        StringBuilder str = new StringBuilder();
        str.append("I have found the following tasks containing '").append(keyword).append("'\n");
        int number = 1;
        for (Task t : list) {
            str.append("\n\t").append(number).append(".").append(t);
            number++;
        }
        str.append("\n");
        return str.toString();
    }

    public static String warnCorruptedLine(FileParseException e) {
        StringBuilder str = new StringBuilder();
        str.append("The following line is corrupted:\n\t").append(e.getMessage());
        str.append("\nPls note that it'll be deleted\n");
        System.out.println(str);
        return str.toString();
    }

    public static String showErrorOccurred(DukeException e) {
        StringBuilder str = new StringBuilder();
        if (e instanceof WrongArgumentException) {
            str.append("'").append(e.getMessage()).append("' is an invalid argument\n");
            str.append(Ui.tryAgain()).append("\n");
        } else if (e instanceof NoArgumentException) {
            str.append(e.getMessage());
            str.append(Ui.tryAgain());
        }
        return str.toString();
    }

    public static String sendMessage(String msg) {
        return msg;
    }

    public static String tryAgain() {
        return "\nPls try again\n";
    }

    public static String showUnknownError() {
        return "A particular error occurred where it's impossible for the error to occur";
    }
}
