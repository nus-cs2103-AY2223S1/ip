package duke.helper;
import duke.task.*;

public class Ui {
    /**
     * Child containing default messages
     */
    public static void welcome() {
        System.out.println("-------------------------------------------");
        System.out.println("Hello from Phil");
        System.out.println("How may I assist you on this fine day?");
        System.out.println("-------------------------------------------");
    }
    public static void bye() {
        System.out.println("See you later alligator!");
        System.out.println("-------------------------------------------");
    }

    public static void add(Task task) {
        System.out.println("added: " + task.toString());
    }

    public static void mark() {
        System.out.println("Roger sir the task has been marked!");
    }

    public static void unmark() {
        System.out.println("Aww okay the task has been unmarked.");
    }

    public static void delete(Task task) {
        System.out.println("Alrighty! I have deleted the following task:");
        System.out.println(task.toString());
    }

    public static void countTasks(TaskList list) {
        System.out.println("We now have " + list.getSize() + " tasks left.");
    }

    public static void clear() {
        System.out.println("The list has been successfully cleared!");
    }
    
    public static void taskFound() {
        System.out.println("Woohoo here are some matches found!");
    }

    public static void noTaskFound() {
        System.out.println("Ohno I could not find any tasks fitting the keywords...");
    }

    public static void line() {
        System.out.println("-------------------------------------------");
    }
}
