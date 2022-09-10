package bobthebot.utils;

import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;

/**
 * Class which handles the interaction of BobTheBot and the user.
 */
public class Ui {
    /**
     * Welcomes the user.
     *
     * @return Welcome message.
     */
    public static String sayWelcome() {
        String toPrint = LanguageBank.WELCOME_MESSAGE;
        formatMessage(toPrint);
        return toPrint;
    }

    /**
     * Says goodbye to the user.
     *
     * @param list ToDoList of the items the user still has to accomplish.
     * @return Goodbye message.
     */
    public static String sayGoodbye(ToDoList list) {
        String result = LanguageBank.GOODBYE_MESSAGE;
        String haveMoreTasks = "You still have " + list.getLength()
                + (list.getLength() == 1 ? " task" : " tasks")
                + " to do! \uD83D\uDE0A";
        String completedAllTasks = LanguageBank.COMPLETED_ALL_TASKS_GOODBYE_MESSAGE;

        result = list.getLength() == 0 ? result + completedAllTasks : result + haveMoreTasks;
        formatMessage(result);
        return result;
    }

    /**
     * Formats and prints the message to the user.
     *
     * @param s The String to be formatted.
     */
    public static void formatMessage(String s) {
        String result = LanguageBank.LINE_BREAK
                + s + "\n"
                + LanguageBank.LINE_BREAK;
        System.out.println(result);
    }

    /**
     * Formats and prints an error message to the user.
     *
     * @param s The error message to be formatted.
     */
    public static void printErrorMessage(String s) {
        String result = LanguageBank.LINE_BREAK
                + s + "\n"
                + LanguageBank.LINE_BREAK;
        System.err.println(result);
    }

    /**
     * Informs the user that the specified task was successfully added.
     *
     * @param t The task that was added.
     * @param list The ToDo List the task was added to.
     * @return Task added message.
     */
    public static String taskAddedMessage(Task t, ToDoList list) {
        String toPrint = LanguageBank.TASK_ADDED_MESSAGE;
        toPrint += "\t" + t.toString() + "\n";
        toPrint += "\tYou now have " + list.getLength() + (list.getLength() == 1 ? " task" : " tasks")
                + " in the list.";
        formatMessage(toPrint);
        return toPrint;
    }

    /**
     * Informs the user that the specified task was successfully deleted.
     *
     * @param t The task to be deleted.
     * @param list The ToDo List that the task is deleted from.
     * @return Task deleted message.
     */
    public static String taskDeletedMessage(Task t, ToDoList list) {
        String toPrint = LanguageBank.TASK_DELETED_MESSAGE;
        toPrint += "\t\t" + t.toString() + "\n";
        toPrint += "\tYou now have "
                + (list.getLength())
                + (list.getLength() == 1 ? " task" : " tasks");
        formatMessage(toPrint);
        return toPrint;
    }

    /**
     * Takes in the 0 index of the item done and prints a message informing the user that their item is
     *      successfully marked done.
     *
     * @param list ToDo List the item is in.
     * @param index The index of the item to be marked as done.
     * @return Mark task done message.
     */
    public static String markItemDoneMessage(ToDoList list, int index) {
        String toPrint = LanguageBank.TASK_MARKED_DONE_MESSAGE;
        toPrint += "\t"
                + list.getTask(index).toString();
        formatMessage(toPrint);
        return toPrint;
    }

    /**
     * Takes in the 0 index of the item done and prints a message informing the user that their item is
     *      successfully marked undone.
     *
     * @param list ToDo List the item is in.
     * @param index The index of the item to be marked as undone.
     * @return Mark task undone message.
     */
    public static String markItemUndoneMessage(ToDoList list, int index) {
        String toPrint = LanguageBank.TASK_MARKED_UNDONE_MESSAGE;
        toPrint += "\t" + list.getTask(index).toString();
        formatMessage(toPrint);
        return toPrint;
    }

    /**
     * Prints items in the list.
     *
     * @param list Tasks to be printed from this TODO list.
     * @return String containing the items left in the list.
     */
    public static String listMessage(ToDoList list) {
        if (list.getLength() == 0) {
            String toPrint = LanguageBank.NO_TASKS_LEFT_MESSAGE;
            formatMessage(toPrint);
            return toPrint;
        } else {
            String toPrint = LanguageBank.TASKS_LEFT_MESSAGE;
            toPrint += list.toString();
            formatMessage(toPrint);
            return toPrint;
        }
    }
}
