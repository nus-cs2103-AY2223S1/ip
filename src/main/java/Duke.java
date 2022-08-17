import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static char triangle = '\u25B8';
    public static String start = triangle + " ";
    public static ArrayList<Task> toDo = new ArrayList<>(100);

    public static void printList() {
        if (toDo.size() == 0) {
            System.out.println(start + "your list is empty. start adding some tasks to do now!");
        } else {
            System.out.println(start + "these are the tasks in your list:");
            int x = 1;
            for (Task task : toDo) {
                System.out.println("  " + x + ". " + task.toString());
                x++;
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(start + "Hi, I'm Duke!\n  What would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {

            String input = sc.nextLine();
            String[] arr = input.split(" ");
            if (arr[0].equals("bye")) {
                System.out.println(start + "Bye! I hope to see you again soon!");
                break;
            }

            try {
                switch (arr[0]) {
                    case "list":
                        printList();
                        break;
                    case "mark":
                        Task doneTask = toDo.get(Integer.parseInt(arr[1]) - 1);
                        doneTask.setDone();
                        System.out.println(start + "good job! this task has been marked as done:");
                        System.out.println("     " + doneTask.toString());
                        break;
                    case "unmark":
                        Task undoneTask = toDo.get(Integer.parseInt(arr[1]) - 1);
                        undoneTask.setUndone();
                        System.out.println(start + "ok, i've marked this task as not done yet:");
                        System.out.println("     " + undoneTask.toString());
                        break;
                    default:
                        toDo.add(new Task(input));
                        System.out.println(start + "added: " + input);
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println(start + "please enter a valid integer from 1 - " + toDo.size());
            }
        }
    }
}
