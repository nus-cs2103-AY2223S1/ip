package duke.util;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Task;
import java.util.ArrayList;

/**
 * For anything related to printing statements in Duke.
 */
public class Ui {
    public static final String[] COMMAND_HELP = new String[] { "todo [description]",
                                                                "deadline [description] /by [dd/MM/yy] <24hr time>",
                                                                "event [description] /at [dd/MM/yy] <24hr time>",
                                                                "mark [index]",
                                                                "unmark [index]",
                                                                "delete [index]",
                                                                "list",
                                                                "bye" };

    public static String showHello() {
        StringBuilder str = new StringBuilder();
        str.append("Hello! What plans do you currently have?");
        str.append("\nUse /? for help");
        return str.toString();
    }

    public static String showBye() {
        return "\tBye! Hope that I was of service!";
    }

    public static String showHelp() {
        StringBuilder str = new StringBuilder();
        str.append("These are the commands available:\n");
        for (String desc : COMMAND_HELP) {
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
        StringBuilder str = new StringBuilder();
        str.append("\tnice! I've marked this task as done:\n");
        str.append("\t\t").append(task).append("\n");
        return str.toString();
    }

    public static String markTaskNotDone(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("\tOk! I've marked this task as not done yet:\n");
        str.append("\t\t").append(task).append("\n");
        return str.toString();
    }

    public static String deleteTask(Task task) {
        StringBuilder str = new StringBuilder();
        str.append("\tOk! I've deleted this task:\n");
        str.append("\t\t").append(task).append("\n");
        return str.toString();
    }

    public static String addTask(Task task) {
        return "\tadded: " + task + "\n";
    }

    public static String getListSize(TaskList list) {
        return "You currently have " + list.getSize() + " tasks in the list\n";
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

    public static String tryAgain() {
        return "\nPls try again\n";
    }

    public static String showUnknownError() {
         return "A particular error occurred where it's impossible for the error to occur";
    }
}
