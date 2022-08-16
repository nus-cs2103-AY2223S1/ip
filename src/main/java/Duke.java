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

    public static ToDo addTodo(String input) {
        ToDo todo = new ToDo(input);
        return todo;
    }

    public static Deadline addDeadline(String input) {
        String[] inputArray = input.split(" /by ", 2);
        String description = inputArray[0];
        String by = inputArray[1];
        Deadline deadline = new Deadline(description, by);
        return deadline;
    }

    public static Event addEvent(String input) {
        String[] inputArray = input.split(" /at ", 2);
        String description = inputArray[0];
        String at = inputArray[1];
        Event event = new Event(description, at);
        return event;
    }

    public static void addTask(String input, int type) {
        Task task = null;
        switch (type) {
            // To-do
            case 0:
                task = addTodo(input);
                break;
            // Deadline
            case 1:
                task = addDeadline(input);
                break;
            // Event
            case 2:
                task = addEvent(input);
                break;
            default:
                break;
        }
        tasks.add(task);
        printLine();
        System.out.println("\t" + " Got it. I've added this task:");
        System.out.println("\t\t" + " " + task);
        if (tasks.size() == 1) {
            System.out.println(String.format("\t Now you have %d task in the list.", tasks.size()));
        } else {
            System.out.println(String.format("\t Now you have %d tasks in the list.", tasks.size()));
        }
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
            String[] inputArray = input.split(" ", 2);
            String firstWord = inputArray[0];
            String secondWord = "";
            if (inputArray.length == 2) {
                secondWord = inputArray[1];
            }
            switch (firstWord) {
                case "bye":
                    if (inputArray.length == 1) {
                        exit();
                        isRunning = false;
                    }
                    break;
                case "list":
                    if (inputArray.length == 1) {
                        listTasks();
                    }
                    break;
                case "mark":
                    markAsDone(Integer.parseInt(inputArray[1]));
                    break;
                case "unmark":
                    markAsNotDone(Integer.parseInt((inputArray[1])));
                    break;
                case "todo":
                    addTask(secondWord, 0);
                    break;
                case "deadline":
                    addTask(secondWord, 1);
                    break;
                case "event":
                    addTask(secondWord, 2);
                    break;
                default:
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
