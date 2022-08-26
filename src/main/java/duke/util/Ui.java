package duke.util;

import duke.exception.DukeException;
import duke.exception.FileParseException;
import duke.exception.NoArgumentException;
import duke.exception.WrongArgumentException;
import duke.task.Task;

import java.util.ArrayList;

public class Ui {
    public static final String[] COMMAND_HELP = new String[] { "todo [description]",
                                                                "deadline [description] /by [dd/MM/yy] <24hr time>",
                                                                "event [description] /at [dd/MM/yy] <24hr time>",
                                                                "list",
                                                                "bye" };

    public static void hello() {
        System.out.println("\nHello! What plans do you currently have?");
        System.out.println("Use '/?' for help");
    }

    public static void bye() {
        System.out.println("\tBye! Hope that I was of service!");
    }

    public static void help() {
        System.out.println("These are the commands available:\n");
        for (String desc : COMMAND_HELP) {
            System.out.println("\t" + desc);
        }
        System.out.println();
    }

    public static void displayList(TaskList list) {
        if (list.getSize() == 0) {
            Ui.listSize(list);
            return;
        }
        System.out.println("Here are your plans:\n");
        int number = 1;
        for (Task task : list.getArray()) {
            System.out.println("\t" + number + "." + task);
            number++;
        }
        System.out.println();
        Ui.listSize(list);
        System.out.println("Pls don't procrastinate on the above tasks!");

    }

    public static void taskMarkedDone(Task task) {
        System.out.println("\tnice! I've marked this task as done:");
        System.out.println("\t\t" + task);
    }

    public static void taskMarkedNotDone(Task task) {
        System.out.println("\tOk! I've marked this task as not done yet:");
        System.out.println("\t\t" + task);
    }

    public static void taskDeleted(Task task) {
        System.out.println("\tOk! I've deleted this task:");
        System.out.println("\t\t" + task);
    }

    public static void taskAdded(Task task) {
        System.out.println("\tadded: " + task);
    }

    public static void findKeyword(String keyword, ArrayList<Task> list) {
        System.out.println("I have found the following tasks containing '" + keyword + "'\n");
        int number = 1;
        for (Task t : list) {
            System.out.println("\t" + number + "." + t);
            number++;
        }
        System.out.println();
    }

    public static void listSize(TaskList list) {
        System.out.println("You currently have " + list.getSize() + " tasks in the list");
    }

    public static void corruptedLine(FileParseException e) {
        System.out.println("The following line has been corrupted:\n\t" + e.getMessage());
        System.out.println("Pls note that it'll be deleted\n");
    }

    public static void errorOccurred(DukeException e) {
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

    public static void unknownError() {
        System.out.println("A particular error occurred where it's impossible for the error to occur");
    }
}
