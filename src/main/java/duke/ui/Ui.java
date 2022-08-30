package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskId;

/**
 * Text UI of application that deals with interactions with the user.
 *
 * @author WR3nd3
 */
public class Ui {
    /** Welcome symbol to be displayed to users upon running Duke in Terminal */
    private static final String CLI_CAT_SYMBOL = "     /\\_____/\\\n"
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

    private static final String GUI_CAT_SYMBOL = "       /\\_____/\\\n"
            + "     /  o        o  \\\n"
            + "    ( ==  ^  == )\n"
            + "    )                    (\n"
            + "   (                      )\n"
            + "  ( (  )              (  ) )\n"
            + " (__(__)___(__)__)\n"
            + "                  _\n"
            + "   ___ __ _| |_ ___\n"
            + " /  __/ _`  |   __/ __|\n"
            + "| (_| (_|  |  |_\\__  \\\n"
            + " \\___\\__,_|\\__|___/\n";

    /** Border enclosing output */
    private static final String MESSAGE_BORDER = "_______________________________________"
            + "______________\n";

    /** Welcome message printed when running duke.Duke */
    private static final String MESSAGE_SERVICE = "What can I do for mew?\n";

    /** Goodbye message printed when exiting duke.Duke */
    private static final String MESSAGE_GOODBYE = "Bye! See nya later!\n";

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
     * Returns String of composed welcome message on running duke.Duke on CLI terminal.
     *
     * @return String of welcome message.
     */
    public String cliShowWelcome() {
        String content = "Meow from\n" + CLI_CAT_SYMBOL + "\n" + MESSAGE_SERVICE;
        return content;
    }

    /**
     * Returns String of composed welcome message on running duke.Duke with GUI.
     *
     * @return String of welcome message.
     */
    public String guiShowWelcome() {
        String content = "Meow from\n" + GUI_CAT_SYMBOL + "\n" + MESSAGE_SERVICE;
        return content;
    }

    /**
     * Returns String of goodbye message on exiting duke.Duke.
     *
     * @return String of goodbye message.
     */
    public String showGoodbye() {
        String content = MESSAGE_GOODBYE;
        return content;
    }

    /**
     * Returns confirmation message that a task has been added to the list.
     *
     * @param task task.Task added to the list.
     * @param tasksLeft Number of tasks left in list.
     * @return String of confirmation message of adding a task.
     */
    public String showAdd(Task task, int tasksLeft) {
        String content = "";
        TaskId id = task.getType();
        switch(id) {
        case T:
            content = "Meow! I'm a cat. I've added this task:\n";
            break;
        case E:
            content = "Moo! I'm a cat. I've added this event:\n";
            break;
        case D:
            content = "Woof! I'm a cat. I've added this deadline:\n";
            break;
        default:
            break;
        }
        String outro = showTasksLeft(tasksLeft);
        return content + outro;
    }

    /**
     * Returns confirmation message that task has been marked as complete.
     *
     * @param t task.Task to be marked.
     * @return String of confirmation message of completing a task.
     */
    public String showMark(Task t) {
        String content = "Nyace! One step closer to nap!\n" + TAB + t + "\n";
        return content;
    }

    /**
     * Returns confirmation message that task has been marked as incomplete.
     *
     * @param t task.Task to be marked as incomplete.
     * @return String of confirmation message of marking a task as incomplete.
     */
    public String showUnmark(Task t) {
        String content = "You nyapped for too long!\n" + TAB + t + "\n";
        return content;
    }

    /**
     * Returns confirmation message that task has been deleted from the list.
     *
     * @param t task.Task to be deleted.
     * @param tasksLeft Integer representing the number of tasks left in the list.
     * @return String of message confirming a delete operation had been carried out.
     */
    public String showDelete(Task t, int tasksLeft) {
        String content = "It's dead!! It's deadsss!\n" + TAB + t + "\n";
        String end = showTasksLeft(tasksLeft);
        return content + end;

    }

    /**
     * Returns current state of the list.
     *
     * @param list String array of task descriptions.
     * @param tasksLeft Integer representing the number of tasks left in the list.
     * @return String of message showing the state of the list and its constituent tasks.
     */
    public String showList(String[] list, int tasksLeft) {
        String content;

        if (tasksLeft == 0) {
            content = "NYAAA! 00 Tasks means nap time.\n";
            return content;
        } else {
            content = "Here nya the tasks in your list:\n";
            StringBuilder builder = new StringBuilder(content);
            for (String s : list) {
                builder.append(s).append("\n");
            }
            return builder.toString();
        }
    }

    /**
     * Returns tasks in the list that are matching.
     *
     * @param list String array of matching task descriptions.
     * @return String showing tasks in list matching the description.
     */
    public String showFindList(String[] list) {
        String content;

        if (list[0] == null) {
            content = "NYAAA! 00 Tasks are found.\n";
            return content;
        } else {
            content = "Here nya the matching tasks in your list:\n";
            StringBuilder builder = new StringBuilder(content);
            for (String s : list) {
                if (s == null) {
                    break;
                }
                builder.append(s).append("\n");
            }
            return builder.toString();
        }
    }

    /**
     * Returns message indicating an empty task list.
     *
     * @return String indicating the list is empty.
     */
    public String showEmpty() {
        String content = "There are NYA tasks hereeeee\n";
        return content;
    }

    /**
     * Returns message indicating the number of tasks left in the list.
     *
     * @param tasksLeft Integer representing the number of tasks left in the list.
     * @return String indicating the number of tasks left in the list.
     */
    public String showTasksLeft(int tasksLeft) {
        String str = "Nyaw you have " + Math.max(tasksLeft, 0) + " ";
        if (tasksLeft > 1) {
            str += "tasks in the list\n";
        } else {
            str += "task in the list\n";
        }
        return str;
    }

    /**
     * Returns error message inputted.
     *
     * @param errMessage String representing error message.
     * @return String containing the error message.
     */
    public String showError(String errMessage) {
        String content = errMessage + "\n";
        return content;
    }

    /**
     * Prints error message indicating file could not be loaded.
     *
     * @return String containing the loading error message.
     */
    public String showLoadingError() {
        String content = "File cannyat load. Please check saved list text\n";
        return content;
    }

    /**
     * Prints a border to enclose output.
     */
    public void showLine() {
        System.out.println(MESSAGE_BORDER);
    }

}
