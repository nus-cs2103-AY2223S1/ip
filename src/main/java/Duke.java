import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static String line = "----------------------------------------";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "");
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        String current = sc.nextLine();
        while (!current.equals("bye")) {
            String[] splitString = current.split(" ");
            current = splitString[0];
            switch (current) {
            case "list":
                printTasks();
                break;
            case "mark":
                int taskIdToMark = Integer.parseInt(splitString[1]);
                changeTaskStatus(taskIdToMark, true);
                break;
            case "unmark":
                int taskIdToUnmark = Integer.parseInt(splitString[1]);
                changeTaskStatus(taskIdToUnmark, false);
                break;
            default:
                current = String.join(" ", splitString);
                System.out.println(line);
                System.out.println("added: " + current);
                addTask(current);
                System.out.println(line);
                break;
            }

            current = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void addTask(String task) {
        Task t = new Task(task);
        tasks.add(t);
    }
    public static void printTasks() {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            System.out.println((i + 1) + "." + current.toString());
        }
        System.out.println(line);
    }

    public static void changeTaskStatus(int taskId, boolean isDone) {
        Task task = tasks.get(taskId - 1);
        task.setDoneStatus(isDone);
        if (isDone) {
            System.out.println(("Nice! I've marked this task as done:"));
            System.out.println("  " + task.toString());
        } else {
            System.out.println(("OK, I've marked this task as not done yet:"));
            System.out.println("  " + task.toString());
        }
        System.out.println(line);
    }
}
