import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void printLine() {
        System.out.println("\t" + "____________________________________________________________");
    }

    public static void greet() {
        printLine();
        String logo = "\t" + "  ____        _        \n"
                + "\t" + " |  _ \\ _   _| | _____ \n"
                + "\t" + " | | | | | | | |/ / _ \\\n"
                + "\t" + " | |_| | |_| |   <  __/\n"
                + "\t" + " |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\t" + " Hello from\n" + logo);
        System.out.println("\t" +" Hello! I'm Duke\n");
        System.out.println("\t" +" What can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\t" + " Bye. Hope to see you again soon!");
        printLine();
    }

    public static void addTask(String t) {
        Task task = new Task(t);
        tasks.add(task);
        printLine();
        System.out.println("\t" + " added: " + t);
        printLine();
    }

    public static void listTasks() {
        printLine();
        System.out.println("\t" + " Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + " " + String.valueOf(i + 1) + ". " + tasks.get(i));
        }
        printLine();
    }

    public static Task getTask(int index) throws Exception {
        int numTasks = tasks.size();
        if (numTasks == 0) {
            throw new Exception("You do not have any tasks.");
        } else if (index < 1) {
            throw new Exception("Task number starts from one.");
        } else if (index > numTasks){
            if (numTasks == 1) {
                throw new Exception(String.format("You only have %d task.", numTasks));
            } else {
                throw new Exception(String.format("You only have %d tasks.", numTasks));
            }
        } else {
            return tasks.get(index - 1);
        }
    }

    public static void markAsDone(int taskNumber) throws Exception {
        Task task = getTask(taskNumber);
        task.markAsDone();
        printLine();
        System.out.println("\t" + " Nice! I've marked this task as done:");
        System.out.println("\t " + task);
        printLine();
    }

    public static void markAsNotDone(int taskNumber) throws Exception {
        Task task = getTask(taskNumber);
        task.markAsNotDone();
        printLine();
        System.out.println("\t" + " OK! I've marked this task as not done yet:");
        System.out.println("\t " + task);
        printLine();
    }

    public static void startDuke() throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ");
            String firstWord = inputArray[0];
            switch (firstWord) {
                case "bye":
                    if (inputArray.length == 1) {
                        exit();
                        isRunning = false;
                        break;
                    } else {
                        continue;
                    }
                case "list":
                    if (inputArray.length == 1) {
                        listTasks();
                        break;
                    } else {
                        continue;
                    }
                case "mark":
                    markAsDone(Integer.parseInt(inputArray[1]));
                    break;
                case "unmark":
                    markAsNotDone(Integer.parseInt((inputArray[1])));
                    break;
                default:
                    addTask(input);
                    break;
            }
        }
    }

    public static void main(String[] args) {
        try {
            greet();
            startDuke();
        } catch (Exception e) {
            printLine();
            System.out.println(e.toString());
            printLine();
        }
    }
}
