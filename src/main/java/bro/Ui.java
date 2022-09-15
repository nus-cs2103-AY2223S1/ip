package bro;

import bro.task.Task;

/**
 * Ui class.
 */
public class Ui {
    /**
     * Prints out the welcome message.
     */
    public static String welcome() {
        return "Hello! I'm THE BRO\n" + "Feel free to add any task!!\nWhen in doubt type 'help'\n";
    }
    /**
     * Prints out the size of the ArrayList.
     * @param list1 The TaskList
     */
    public static String listSize(TaskList list1) {
        if (list1.size() == 1) {
            return "You only have " + list1.size() + " task left!" + "\n";
        } else {
            return "You only have " + list1.size() + " tasks left!" + "\n";
        }
    }

    /**
     * Prints the toString of the task.
     * @param t The task given by the user.
     */
    public static String printAdd(Task t) {
        return t.toString() + "\n";
    }

    /**
     * Prints out the mark statement.
     * @param list1 ArrayList of Task where the task has to be marked.
     * @param n Index of the task to be marked.
     */
    public static String markUi(TaskList list1, int n) {
        return "I have marked this task\n" + (list1.get(n - 1)).toString() + "\n";
    }

    /**
     * Prints out the unmark statement.
     * @param list1 ArrayList of Task where the task has to be unmarked.
     * @param n Index of the task to be unmarked.
     */
    public static String unmarkUi(TaskList list1, int n) {
        return "I have unmarked this task\n" + (list1.get(n - 1)).toString() + "\n";
    }

    /**
     * Prints out the delete statement.
     * @param t TaskList of Task where the task has to be deleted.
     */
    public static String deleteUi(Task t) {
        return "I have removed this task.\n" + t.toString() + "\n";
    }

    /**
     * Prints out the bye statement.
     */
    public static String bye() {
        return "See you later broo!";
    }

    /**
     * Shows the error message for the file not being able to load.
     */
    public static String showLoadingError() {
        return "Sorry! The file couldn't be loaded." + "\n";
    }

    /**
     * Prints out the message given.
     * @param msg The message to be printed out.
     */
    public static String errorMessage(String msg) {
        return msg + "\n";
    }
    /**
     * Returns the task with the keyword.
     * @param tasks The ArrayList containing the tasks.
     * @return The tasks with keyword.
     */
    public static String findUi(TaskList tasks) {
        String result = "";
        if (tasks.size() == 0) {
            result += "Sry! Word could not be found!";
        } else {
            int count = 1;
            for (int i = 0; i < tasks.size(); i++) {
                result += count + "." + tasks.get(i) + "\n";
                count++;
            }
        }
        return "Here are the tasks: \n" + result;
    }

    /**
     * Returns the help page for user.
     * @return The command needed to use chatbot.
     */
    public static String helpUi() {
        return " COMMAND" + "\t\t" + "PURPOSE" + "\n"
                + "1. list :\n\t\tTo view the tasks left \n"
                + "2. todo descr :\n\t\tTo add todo task \n"
                + "3. deadline descr /by dd/mm/yyyy hhmm :\n\t\tTo add task with deadline\n"
                + "4. event descr /at dd/mm/yyyy hhmm :\n\t\tTo add an event\n"
                + "5. mark index :\n\t\tTo mark the task as done \n"
                + "6. unmark index :\n\t\tTo mark the task as not done \n"
                + "7. delete index :\n\t\tTo delete the task in that index \n"
                + "8. find keyword :\n\t\tTo find task with the keyword \n"
                + "9. bye :\n\t\tTo exit the chatbot";
    }
}
