import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        welcome();
    }

    /**
     * Echos the input and stores it in the list
     * Exit when user types "bye"
     **/
    public static void welcome() {
        list = new ArrayList<>();
        System.out.println("Hello! I'm Botson");
        Scanner input = new Scanner(System.in);
        System.out.println("What can I help you with? :-)");
        System.out.println("--------------------------");
        while (input.hasNext()) {
            String action = input.nextLine();  // Read user input
            if (Objects.equals(action.strip(), "bye")) {
                System.out.println("Goodbye! Hope to see you again soon!");
                System.out.println("--------------------------");
                break;
            } else if (Objects.equals(action.strip(), "list")) {
                getList();
            } else if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "mark")) {
                markTask(action);
            } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "unmark")) {
                unMarkTask(action);
            } else if (action.length() >= 6 && Objects.equals(action.substring(0, 6), "delete")) {
                deleteTask(action);
            } else {
                addToList(action);
            }
        }
    }

    /**
     * Deletes specified task
     * @param action takes in action from input
     */
    public static void deleteTask(String action) {
        try {
            String i = action.substring(6).replaceAll(" ", "");
            int index = Integer.parseInt(i) - 1;
            String removedTask = list.get(index).toString();
            list.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println("--------------------------");
        }
        catch (Exception e) {
            System.out.println("OOPS!!! Error: No Such Task");
            System.out.println("--------------------------");
        }
    }

    /**
     * determines which type of task and input into list accordingly
     * @param action takes in action from input
     */
    public static void addToList(String action) {
        try {
            if (action.length() >= 4 && Objects.equals(action.substring(0, 4), "todo")) {
                list.add(new TodoTask(action.substring(4).strip()));
            } else if (action.length() >= 5 && Objects.equals(action.substring(0, 5), "event")) {
                int i = action.indexOf('/');
                String event = action.substring(i + 3).strip();
                list.add(new EventTask(action.substring(5, i).strip(), event));
            } else if (action.length() >= 8 && Objects.equals(action.substring(0, 8), "deadline")) {
                int i = action.indexOf('/');
                String by = action.substring(i + 3).strip();
                list.add(new DeadlineTask(action.substring(8, i).strip(), by));
            } else {
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(!!!!");
            }
            System.out.println("--------------------------");
            System.out.println("I've added to the list:\n" + list.get(list.size() - 1));
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            System.out.println("--------------------------");
        }
        catch (Exception e) {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            System.out.println("--------------------------");
        }
    }

    /**
     * prints out the list of tasks added
     */
    public static void getList() {
        System.out.println("Here are the tasks in your list");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
        System.out.println("--------------------------");
    }

    /**
     * edits the task to check if mark or unmarked
     * @param action action of task
     * @param mark status if mark or unmark
     */
    public static void editTask(String action, boolean mark) {
        try {
            String i = mark ? action.substring(4): action.substring(6);
            i = i.replaceAll(" ", "");
            int index = Integer.parseInt(i) - 1;
            if (mark) {
                list.get(index).markDone();
            } else {
                list.get(index).markUnDone();
            }
            String output = mark ? "Nice! I've marked this task as done:" : "OK, I've marked this task as not done yet:";
            System.out.println(output);
            System.out.println(list.get(index).toString());
            System.out.println("--------------------------");
        }
        catch (Exception e) {
            System.out.println("OOPS!!! Error: No Such Task");
            System.out.println("--------------------------");
        }
    }

    /**
     * marks the task to done
     * @param action task action
     */
    public static void markTask(String action) {
        editTask(action, true);
    }

    /**
     * set the task to undone
     * @param action task action
     */
    public static void unMarkTask(String action) {
        editTask(action, false);
    }
}
