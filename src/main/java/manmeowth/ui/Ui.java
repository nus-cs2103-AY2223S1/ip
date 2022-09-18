package manmeowth.ui;

import java.util.Scanner;

import manmeowth.task.Task;
import manmeowth.task.TaskId;

/**
 * Text UI of application that deals with interactions with the user.
 *
 * @author WR3nd3
 */
public class Ui {
    /** Welcome symbol to be displayed to users upon running ManMeowth in Terminal */
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

    private static final String GUI_CAT_SYMBOL = "        /\\_____/\\\n"
            + "      /  o     o  \\\n"
            + "    ( ==  ^  == )\n"
            + "    )                (\n"
            + "   (                  )\n"
            + "  ( (  )         (  ) )\n"
            + " (__(__)___(__)__)\n"
            + "               _\n"
            + "   ___ __ _| |_ ___\n"
            + " / __/ _`  |  __/ __|\n"
            + "| (_| (_|  |  |_\\__  \\\n"
            + " \\___\\__,_|\\__|___/\n";

    /** Border enclosing output */
    private static final String MESSAGE_BORDER = "_______________________________________"
            + "______________\n";

    /** Welcome message printed when running manmeowth.ManMeowth */
    private static final String MESSAGE_SERVICE = "What can I do for mew?\n";

    /** Goodbye message printed when exiting manmeowth.ManMeowth */
    private static final String MESSAGE_GOODBYE = "Bye! See nya later!\n";

    /** Help messages printed to advise command usage **/
    private static final String HELP_INTRO = "Here nya the valid instructions! Rub my belly!\n";
    private static final String HELP_TODO = "Input 'todo ABC' to add task ABC\n";
    private static final String HELP_EVENT = "Input 'event ABC /at DATE' to add event ABC on"
            + " DATE\n";
    private static final String HELP_DEADLINE = "Input 'deadline ABC /by DATE' to add deadline"
            + " ABC due by DATE\n";
    private static final String HELP_MARK = "Input 'mark xxx' to mark task xxx as complete\n";
    private static final String HELP_UNMARK = "Input 'unmark xxx' to mark task xxx as incomplete\n";
    private static final String HELP_DELETE = "Input 'delete xxx' to delete task xxx from the list\n";
    private static final String HELP_LIST = "Input 'list' for overview\n";
    private static final String HELP_HELP = "Input 'help' for command overview\n";
    private static final String HELP_BYE = "Input 'bye' to exit.\n";
    private static final String HELP_OUTRO = "NYAAAAAA!\n";
    private static final String ARROW = "=> ";

    /** Default help message for commands */
    private static final String HELP_MSG = HELP_INTRO
            + ARROW + HELP_TODO
            + ARROW + HELP_EVENT
            + ARROW + HELP_DEADLINE
            + ARROW + HELP_MARK
            + ARROW + HELP_UNMARK
            + ARROW + HELP_DELETE
            + ARROW + HELP_LIST
            + ARROW + HELP_HELP
            + ARROW + HELP_BYE
            + "\n" + HELP_OUTRO;


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
     * Returns String of composed welcome message on running manmeowth.ManMeowth on CLI terminal.
     *
     * @return String of welcome message.
     */
    public String cliShowWelcome() {
        String content = "Meow from\n" + CLI_CAT_SYMBOL + "\n" + MESSAGE_SERVICE;
        return content;
    }

    /**
     * Returns String of composed welcome message on running manmeowth.ManMeowth with GUI.
     *
     * @return String of welcome message.
     */
    public String guiShowWelcome() {
        String content = "Meow from\n" + GUI_CAT_SYMBOL + "\n" + MESSAGE_SERVICE;
        return content;
    }

    /**
     * Returns String of goodbye message on exiting manmeowth.ManMeowth.
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
        String content = "Nyace! One step closer to nap!\n" + ARROW + t + "\n";
        return content;
    }

    /**
     * Returns confirmation message that task has been marked as incomplete.
     *
     * @param t task.Task to be marked as incomplete.
     * @return String of confirmation message of marking a task as incomplete.
     */
    public String showUnmark(Task t) {
        String content = "You nyapped for too long!\n" + ARROW + t + "\n";
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
        String content = "It's dead!! It's deadsss!\n" + ARROW + t + "\n";
        String end = showTasksLeft(tasksLeft);
        return content + end;

    }

    /**
     * Returns current state of the list.
     *
     * @param list String array of task descriptions.
     * @return String of message showing the state of the list and its constituent tasks.
     */
    public String showList(String[] list) {
        String content;

        if (list.length == 0) {
            content = "NYAAA! 00 Tasks means nap time.\n";
            return content;
        } else {
            content = "Here nya the tasks in your list:\n";
            StringBuilder builder = new StringBuilder(content);
            int counter = 1;
            for (String s : list) {
                builder.append(counter)
                        .append(". ")
                        .append(s)
                        .append("\n");
                counter++;
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

        if (list.length == 0) {
            content = "NYAAA! 00 Tasks are found.\n";
            return content;
        } else {
            content = "Here nya the matching tasks in your list:\n";
            StringBuilder builder = new StringBuilder(content);
            int counter = 1;
            for (String s : list) {
                builder.append(counter)
                        .append(". ")
                        .append(s)
                        .append("\n");
                counter++;
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
        if (tasksLeft == 1) {
            str += "task in the list\n";
        } else {
            str += "tasks in the list\n";
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
     * Returns error message indicating file could not be loaded.
     *
     * @return String containing the loading error message.
     */
    public String showLoadingError() {
        String content = "File cannyat load. Please check saved list-text.\n";
        return content;
    }

    /**
     * Prints a border to enclose output.
     */
    public void showLine() {
        System.out.println(MESSAGE_BORDER);
    }

    /**
     * Returns help message to advise command usage.
     *
     * @return String containing basic command formats.
     */
    public String showHelp() {
        return HELP_MSG;
    }

}
