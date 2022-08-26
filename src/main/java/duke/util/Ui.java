package duke.util;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Task;

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

    public static void showHello() {
        System.out.println("\nHello! What plans do you currently have?");
        System.out.println("Use /? for help");
    }

    public static void showBye() {
        System.out.println("\tBye! Hope that I was of service!");
    }

    public static void showHelp() {
        System.out.println("These are the commands available:\n");
        for (String desc : COMMAND_HELP) {
            System.out.println("\t" + desc);
        }
        System.out.println();
    }

    public static void displayList(TaskList list) {
        if (list.getSize() == 0) {
            Ui.getListSize(list);
            return;
        }
        System.out.println("Here are your plans:\n");
        int number = 1;
        for (Task task : list.getArray()) {
            System.out.println("\t" + number + "." + task);
            number++;
        }
        System.out.println();
        Ui.getListSize(list);
        System.out.println("Pls don't procrastinate on the above tasks!");

    }

    public static void markTaskDone(Task task) {
        System.out.println("\tnice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public static void markTaskNotDone(Task task) {
        System.out.println("\tOk! I've marked this task as not done yet:");
        System.out.println("\t\t" + task);
    }

    public static void deleteTask(Task task) {
        System.out.println("\tOk! I've deleted this task:");
        System.out.println("\t\t" + task);
    }

    public static void addTask(Task task) {
        System.out.println("\tadded: " + task);
    }

    public static void getListSize(TaskList list) {
        System.out.println("You currently have " + list.getSize() + " tasks in the list");
    }

    public static void warnCorruptedLine(FileParseException e) {
        System.out.println("The following line is corrupted:\n\t" + e.getMessage());
        System.out.println("Pls note that it'll be deleted\n");
    }

    public static void showErrorOccurred(DukeException e) {
        if (e instanceof WrongArgumentException) {
            System.out.println("'" + e.getMessage() + "' is an invalid argument");
            Ui.tryAgain();
        } else if (e instanceof NoArgumentException) {
            System.out.println(e.getMessage());
            Ui.tryAgain();
        }
    }

    public static void tryAgain() {
        System.out.println("Pls try again");
    }

    public static void showUnknownError() {
        System.out.println("A particular error occurred where it's impossible for the error to occur");
    }
}
