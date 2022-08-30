package bobthebot.utils;

import bobthebot.tasks.Task;
import bobthebot.tasks.ToDoList;

/**
 * Class which handles the interaction of BobTheBot and the user.
 */
public class Ui {
    /**
     * Welcomes the user.
     */
    public static void printWelcome() {
        String result = "\tHello! I am Bob the Bot, your friendly task manager! \uD83D\uDE0A\n";
        result += "\tWhen using me, please stick to the following commands:\n"
                + "\t\t1. todo - for items that you have to do\n"
                + "\t\t2. deadline - for items which have an upcoming deadline\n"
                + "\t\t3. event - for events with a date and time\n"
                + "\n"
                + "\t\t4. mark - to mark an event as done\n"
                + "\t\t5. unmark - to mark an event as undone\n"
                + "\t\t6. delete - to delete an event\n"
                + "\t\t7. list - to view all the events on your todo list\n"
                + "\t\t8. bye - to wish me a (temporary) farewell";

        formatMessage(result);
    }

    /**
     * Says goodbye to the user.
     *
     * @param list ToDoList of the items the user still has to accomplish.
     */
    public static void printGoodbye(ToDoList list) {
        String result = "\tBye! Hope to see you again soon! ";
        String haveMoreTasks = "You still have " + list.getLength()
                + (list.getLength() == 1 ? " task" : "bobthebot/tasks")
                + " to do! \uD83D\uDE0A";
        String completedAllTasks = "I'm so happy that you've completed all your tasks! \n\tCome back soon "
                + "if you want to accomplish more things! \uD83D\uDE0A";

        formatMessage(list.getLength() == 0 ? result + completedAllTasks : result + haveMoreTasks);
    }

    /**
     * Formats and prints the message to the user.
     *
     * @param s The String to be formatted.
     */
    public static void formatMessage(String s) {
        String result = "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"
                + s + "\n"
                + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
        System.out.println(result);
    }

    /**
     * Formats and prints an error message to the user.
     *
     * @param s The error message to be formatted.
     */
    public static void printErrorMessage(String s) {
        String result = "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n"
                +
                s + "\n"
                + "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n";
        System.err.println(result);
    }

    /**
     * Informs the user that the specified task was successfully added.
     *
     * @param t The task that was added.
     * @param list The ToDo List the task was added to.
     */
    public static void taskAddedMessage(Task t, ToDoList list) {
        String toPrint = "\tGot it. I've added this task: \n";
        toPrint += "\t" + t.toString() + "\n";
        toPrint += "\tYou now have " + list.getLength() + (list.getLength() == 1 ? " task" : "bobthebot/tasks")
                + " in the list.";
        formatMessage(toPrint);
    }

    /**
     * Informs the user that the specified task was successfully deleted.
     *
     * @param index The index of the task to be deleted.
     * @param list The ToDo list which the task is to be deleted from.
     */
    public static void taskDeletedMessage(int index, ToDoList list) {
        String toPrint = "\tGot it. I've removed this task: \n";
        toPrint += "\t\t" + list.getTask(index).toString() + "\n";
        toPrint += "\tYou now have "
                + (list.getLength() - 1)
                + (list.getLength() - 1 == 1 ? " task" : "bobthebot/tasks");
        formatMessage(toPrint);
    }

    /**
     * Takes in the 0 index of the item done and prints a message informing the user that their item is
     *      successfully marked done.
     *
     * @param list ToDo List the item is in.
     * @param index The index of the item to be marked as done.
     */
    public static void markItemDoneMessage(ToDoList list, int index) {
        String toPrint = "\tGOOD JOB! I'm marking this task as done: \n";
        toPrint += "\t"
                + list.getTask(index).toString();
        formatMessage(toPrint);
    }

    /**
     * Takes in the 0 index of the item done and prints a message informing the user that their item is
     *      successfully marked undone.
     *
     * @param list ToDo List the item is in.
     * @param index The index of the item to be marked as undone.
     */
    public static void markItemUndoneMessage(ToDoList list, int index) {
        String toPrint = "\tIt's sad that you thought you finished your work but didnt.\n";
        toPrint += "\t" + "But alright, marking this task as undone: \n";
        toPrint += "\t" + list.getTask(index).toString();
        formatMessage(toPrint);
    }

    /**
     * Prints items in the list.
     *
     * @param list Tasks to be printed from this TODO list.
     */
    public static void listMessage(ToDoList list) {
        if (list.getLength() == 0) {
            String toPrint = "\tYAY! There are no items in your list!";
            formatMessage(toPrint);
        } else {
            formatMessage(list.toString());
        }
    }
}
