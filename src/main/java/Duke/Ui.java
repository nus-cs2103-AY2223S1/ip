package Duke;

import java.util.ArrayList;

/**
 * The class deals with the interaction with the user
 */
public class Ui {

    public Ui() {}

    /**
     * The method is a static and takes in a parameter
     * @param tasks The input to be received
     */
    public static void printList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int j = 0; j < tasks.getSize(); j++) {
            System.out.println((j + 1) + ". " + tasks.getTask(j).toString());
        }
    }

    /**
     * The method is a static and prints a statement
     */
    public static void printHelloMsg() {
        System.out.println("Hi I'm Duke.Duke, What can I do for you?");
    }

    /**
     * The method is a static and takes in two parameter
     * @param task of type Task
     * @param size of type int
     */
    public static void printAddSuccessfulMsg(Task task, int size) {
        System.out.println("Got it! I've added this task:\n"
                + task.toString()
                + "\nNow you've got " + size + " tasks in the list!");
    }

    /**
     * The method is a static and takes in a parameter
     * @param tasks The input to be received
     */
    public static void printFindTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i ++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    /**
     * The method is a static and takes in two parameter
     * @param task of type Task
     * @param size of type int
     */
    public static void printDeleteSuccessfulMsg(Task task, int size) {
        System.out.println("Noted. I've removed this task:\n" + task.toString() +
                "\nNow you have " + size + " tasks in the list.");
    }

    /**
     * The method is a static and takes in a parameter
     * @param task The input to be received
     */
    public static void printMarkTaskSuccessfulMsg(Task task) {
        System.out.println("Nice! I have mark this task as done:\n" + task);
    }

    /**
     * The method is a static and takes in a parameter
     * @param task The input to be received
     */
    public static void printUnMarkTaskSuccessfulMsg(Task task) {
        System.out.println("Ok, I have unmark this task to not done:\n" + task);
    }

    /**
     * The method is a static and prints a statement
     */
    public static void printDontUnderstandMsg() {
        System.out.println("OOPS!!! I don't understand what that means!");
    }

    /**
     * The method is a static and takes in a parameter
     * @param typeStr The input to be received
     */
    public static void printDescriptionCantBeEmptyMsg(String typeStr) {
        System.out.println("OOPS!!! The description of a " + typeStr + " cannot be empty");
    }

    /**
     * The method is a static and takes in a parameter
     * @param e The input to be received
     */
    public static void printErrorMessage(String e) {
        System.out.println(e);
    }
}
