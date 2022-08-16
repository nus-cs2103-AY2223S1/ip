import java.util.ArrayList;

public class UserInterface {
    static void printList(ArrayList<? super Task> list) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i).toString());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    static <T extends Task> void addTaskMessage(T task, int taskCount) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, i've added this task:");
        System.out.println(task.toString());
        System.out.println("You now have " + taskCount + " tasks in the list.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    static void markTaskMessage(ArrayList<? extends Task> list, int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("I've marked this task as done");
        System.out.println(list.get(taskIndex).toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void unmarkTaskMessage(ArrayList<? extends Task> list, int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Ok, i've unmarked this task");
        System.out.println(list.get(taskIndex).toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void taskDeletedMessage(ArrayList<? extends Task> list, int taskIndex) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Got it, i've deleted this task");
        System.out.println(list.get(taskIndex).toString());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    static void sayBye() {
        System.out.println("bai bai");
    }

}
