import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

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
            String command = splitString[0];
            switch (command) {
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
            case "todo":
                String[] descTodo = Arrays.copyOfRange(splitString, 1, splitString.length);
                current = String.join(" ", descTodo);
                Task todoTask = new Todo(current);
                addTask(todoTask);
                break;
            case "deadline":
                String[] descDeadline = Arrays.copyOfRange(splitString, 1, splitString.length);
                current = String.join(" ", descDeadline);

                String descD = current.split("/")[0].trim();
                String[] byDArray = current.split("/")[1].split(" ");
                String byD = String.join(" ", Arrays.copyOfRange(byDArray, 1, byDArray.length));
                Task deadlineTask = new Deadline(descD, byD);
                addTask(deadlineTask);
                break;
            case "event":
                String[] descEvent = Arrays.copyOfRange(splitString, 1, splitString.length);
                current = String.join(" ", descEvent);
                String descE = current.split("/")[0].trim();
                String[] atEArray = current.split("/")[1].split(" ");
                String atE = String.join(" ", Arrays.copyOfRange(atEArray, 1, atEArray.length));
                Task eventTask = new Event(descE, atE);
                addTask(eventTask);
                break;
            default:
                System.out.println(line);
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :<");
                System.out.println(line);
                break;
            }

            current = sc.nextLine();
        }

        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }

    public static void addTask(Task task) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        tasks.add(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(line);
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
