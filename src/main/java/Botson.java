import java.util.Objects;
import java.util.Scanner;

public class Botson {
    static Task[] list = new Task[100];
    public static void main(String[] args) {
        welcome();
    }

    /**
     * Echos the input and stores it in the list
     * Exit when user types "bye"
     **/
    public static void welcome() {
        list = new Task[100];
        int idx = 0;
        System.out.println("Hello! I'm Botson");
        Scanner input = new Scanner(System.in);
        System.out.println("What can I help you with?");
        System.out.println("--------------------------");
        while (input.hasNext()) {
            String action = input.nextLine();  // Read user input
            if (Objects.equals(action, "bye")) {
                System.out.println("Goodbye! Hope to see you again soon!");
                System.out.println("--------------------------");
                break;
            } else if (Objects.equals(action, "list")) {
                getList(idx);
                continue;
            } else if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "mark")) {
                markTask(action);
                continue;
            } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "unmark")) {
                unMarkTask(action);
                continue;
            }
            list[idx] = new Task(action);
            idx++;
            System.out.println("Added: " + action);
            System.out.println("--------------------------");
        }
    }

    /**
     * prints out the list of tasks added
     * @param idx takes in index
     */
    private static void getList(int idx) {
        for (int i = 0; i < idx; i++) {
            System.out.println((i + 1) + ". " + list[i]);
        }
        System.out.println("--------------------------");
    }


    /**
     * edits the task to check if mark or unmarked
     * @param action action of task
     * @param mark status if mark or unmark
     */
    private static void editTask(String action, boolean mark) {
        try {
            String i = mark ? action.substring(4): action.substring(6);
            i = i.replaceAll(" ", "");
            int idx = Integer.parseInt(i) - 1;
            if (mark) {
                list[idx].markDone();
            } else {
                list[idx].markUnDone();
            }
            String output = mark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
            System.out.println(output);
            System.out.println(list[idx].toString());
            System.out.println("--------------------------");
        }
        catch (Exception e) {
            System.out.println("Error: No Such Task");
        }
    }

    /**
     * marks the task to done
     * @param action task action
     */
    private static void markTask(String action) {
        editTask(action, true);
    }

    /**
     * set the task to undone
     * @param action task action
     */
    private static void unMarkTask(String action) {
        editTask(action, false);
    }
}
