package duke.ui;

import duke.task.Task;
import duke.task.TaskId;

import java.util.Scanner;

/**
 * Text UI of application that deals with interactions with the user.
 *
 * @author WR3nd3
 */
public class Ui {
    /** Welcome symbol to be displayed to users upon running duke.Duke */
    private static final String CAT_SYMBOL = "     /\\_____/\\\n"
            + "    /  o   o  \\\n"
            + "   ( ==  ^  == )\n"
            + "    )         (\n"
            + "   (           )\n"
            + "  ( (  )   (  ) )\n"
            + " (__(__)___(__)__)\n"
            + "           _\n"
            + "  ___ __ _| |_ ___\n"
            + " / __/ _` | __/ __|\n"
            + "| (_| (_| | |_\\__ \\\n"
            + " \\___\\__,_|\\__|___/\n";

    /** Border enclosing output */
    private static final String BORDER = "____________________________________________________________\n";

    /** Welcome message printed when running duke.Duke */
    private static final String SERVICE = "What can I do for mew?\n";

    /** Goodbye message printed when exiting duke.Duke */
    private static final String GOODBYE = "Bye! See nya later!\n";

    private static final String TAB = "    ";

    /**
     * Reads and returns user input.
     *
     * @return String of user input.
     */
    public String readCommand() {
        String input = "";
        Scanner sc = new Scanner(System.in);

        if (sc.hasNextLine()) {
            input = sc.nextLine().trim();
        }
        return input;
    }

    /**
     * Prints a border to enclose output.
     */
    public void showLine() {
        System.out.println(BORDER);
    }

    /**
     * Prints composed welcome message on running duke.Duke.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Meow from\n" + CAT_SYMBOL + "\n" + SERVICE);
        showLine();
    }

    /**
     * Prints goodbye message on exiting duke.Duke.
     */
    public void showGoodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints confirmation message that a task has been added to the list.
     *
     * @param task task.Task added to the list.
     * @param tasksLeft Number of tasks left in list.
     */
    public void showAdd(Task task, int tasksLeft) {
        String message = "";
        TaskId id = task.getType();
        switch(id) {
        case T:
            message = "Meow! I'm a cat. I've added this task:\n";
            break;
        case E:
            message = "Moo! I'm a cat. I've added this event:\n";
            break;
        case D:
            message = "Woof! I'm a cat. I've added this deadline:\n";
            break;
        default:
            break;
        }
        System.out.println(message + task + "\n");
        showTasksLeft(tasksLeft);
    }

    /**
     * Prints confirmation message that task has been marked as complete.
     *
     * @param t task.Task to be marked.
     */
    public void showMark(Task t) {
        String msg = "Nyace! One step closer to nap!\n";
        System.out.println(msg + TAB + t);
    }

    /**
     * Prints confirmation message that task has been marked as incomplete.
     *
     * @param t task.Task to be marked as incomplete.
     */
    public void showUnmark(Task t) {
        String msg = "You nyapped for too long!\n";
        System.out.println(msg + TAB + t);
    }

    /**
     * Prints confirmation message that task has been deleted from the list.
     *
     * @param t task.Task to be deleted.
     * @param tasksLeft Integer representing the number of tasks left in the list.
     */
    public void showDelete(Task t, int tasksLeft) {
        String msg = "It's dead!! It's deadsss!\n";
        System.out.println(msg + TAB + t + "\n");
        showTasksLeft(tasksLeft);
    }

    /**
     * Prints current state of the list.
     *
     * @param list String array of task descriptions.
     * @param tasksLeft Integer representing the number of tasks left in the list.
     */
    public void showList(String[] list, int tasksLeft) {
        String message;
        if (tasksLeft == 0) {
            message = "NYAAA! 00 Tasks means nap time.\n";
            System.out.println(message);
        } else {
            message = "Here nya the tasks in your list:\n";
            StringBuilder builder = new StringBuilder(message);
            for (String s : list) {
                builder.append(s).append("\n");
            }
            System.out.println(builder);
        }
    }

    /**
     * Prints message indicating an empty task list.
     */
    public void showEmpty() {
        System.out.println("There are NYA tasks hereeeee");
    }

    /**
     * Prints message indicating the number of tasks left in the list.
     *
     * @param tasksLeft Integer representing the number of tasks left in the list.
     */
    public void showTasksLeft(int tasksLeft) {
        String str = "Nyaw you have " + Math.max(tasksLeft, 0) + " ";
        if (tasksLeft > 1) {
            str += "tasks in the list";
        } else {
            str += "task in the list";
        }
        System.out.println(str);
    }

    /**
     * Prints error message inputted.
     *
     * @param errMessage String representing error message.
     */
    public void showError(String errMessage) {
        System.out.println(errMessage);
    }

    /**
     * Prints error message indicating file could not be loaded.
     */
    public void showLoadingError() {
        System.out.println("File cannyat load. Please check saved list text");
    }

}
