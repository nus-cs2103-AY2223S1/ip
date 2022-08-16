import java.util.ArrayList;

public class UserInterface {
    static void printList(ArrayList<Task> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + ".[" + list.get(i).getStatusIcon() + "] " + list.get(i));
        }
    }
    static void addItemMessage(String input) {
        System.out.println("added :" + input);
    }

    static void markTaskMessage(ArrayList<Task> list, int taskIndex) {
        System.out.println("i've marked this task as done");
        System.out.println("  [" + list.get(taskIndex).getStatusIcon() + "] " + list.get(taskIndex));
    }
    static void sayBye() {
        System.out.println("bai bai");
    }

    public static void unmarkTaskMessage(ArrayList<Task> list, int taskIndex) {
        System.out.println("ok, i've unmarked this task");
        System.out.println("  [" + list.get(taskIndex).getStatusIcon() + "] " + list.get(taskIndex));
    }
}
