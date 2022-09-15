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
        String response = LanguageBank.WELCOME_MESSAGE;
        return response;
    }

    /**
     * Says goodbye to the user.
     *
     * @param list ToDoList of the items the user still has to accomplish.
     * @return Goodbye message.
     */
    public static String sayGoodbye(ToDoList list) {
        String response = "\n" + LanguageBank.GOODBYE_MESSAGE;
        String haveMoreTasks = " You still have " + list.getLength()
                + (list.getLength() == 1 ? " task" : " tasks")
                + " to do! \uD83D\uDE0A";
        String completedAllTasks = LanguageBank.COMPLETED_ALL_TASKS_GOODBYE_MESSAGE;

        response = list.getLength() == 0 ? response + completedAllTasks : response + haveMoreTasks;
        return response;
    }

    /**
     * Formats and prints an error message to the user.
     *
     * @param s The error message to be formatted.
     */
    public static void printErrorMessage(String s) {
        String response = LanguageBank.LINE_BREAK
                + s + "\n"
                + LanguageBank.LINE_BREAK;
        System.err.println(response);
    }

    /**
     * Informs the user that the specified task was successfully added.
     *
     * @param t The task that was added.
     * @param list The ToDo List the task was added to.
     * @return Task added message.
     */
    public static String taskAddedMessage(Task t, ToDoList list) {
        String response = LanguageBank.TASK_ADDED_MESSAGE;
        response += "\t" + t.toString() + "\n";
        response += "\tYou now have " + list.getLength() + (list.getLength() == 1 ? " task" : " tasks")
                + " in the list.";
        return response;
    }

    /**
     * Informs the user that the specified task was successfully deleted.
     *
     * @param t The task to be deleted.
     * @param list The ToDo List that the task is deleted from.
     * @return Task deleted message.
     */
    public static String taskDeletedMessage(Task t, ToDoList list) {
        String response = LanguageBank.TASK_DELETED_MESSAGE;
        response += "\t\t" + t.toString() + "\n";
        response += "\tYou now have "
                + (list.getLength())
                + (list.getLength() == 1 ? " task" : " tasks");
        return response;
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
        String response = LanguageBank.TASK_MARKED_DONE_MESSAGE;
        response += "\t"
                + list.getTask(index).toString();
        return response;
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
        String response = LanguageBank.TASK_MARKED_UNDONE_MESSAGE;
        response += "\t" + list.getTask(index).toString();
        return response;
    }

    /**
     * Prints items in the list.
     *
     * @param list Tasks to be printed from this TODO list.
     * @return String containing the items left in the list.
     */
    public static String listMessage(ToDoList list) {
        if (list.getLength() == 0) {
            String response = LanguageBank.NO_TASKS_LEFT_MESSAGE;
            return response;
        } else {
            String response = LanguageBank.TASKS_LEFT_MESSAGE;
            response += list.toString();
            return response;
        }
    }
}
