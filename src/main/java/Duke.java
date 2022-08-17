import java.util.ArrayList;
import java.util.Scanner;

// This class is the main logic unit for Duke
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Displays the Greeting message.
     */
    private static void Greet() {
        String logo = " _______               \n"
                    + "|  _____|  _   _____   \n"
                    + "|  |____  | | |  __ |  \n"
                    + "|   ____| | | |  ___|  \n"
                    + "|  |____  | | | |      \n"
                    + "|_______| |_| |_|";
        System.out.println("Greetings from Elp\n" + logo);
        System.out.println("What can I help you with?");
    }

    /**
     * Handles Printing of the task list.
     */
    private static void printTaskList() {
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1).toString());
        }
        System.out.println("");
    }

    /**
     * Handles the logic of adding tasks.
     *
     * @param in input string for the task creation
     */
    private static void addTask(String in, String type) {
        Task task;
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {

            if (type.equals("todo")) {
                task = new ToDo(in);
            } else if (type.equals("deadline")) {
                task = new Deadline(in);
            } else {
                task = new Event(in);
            }
            System.out.println("Added: " + task.toString() + "\n");
            tasks.add(task);
        } else {
            System.out.println("Invalid Task");
        }
    }

    private static void taskMarker(String markStatus, String[] inputArr) {
        int taskNo = Integer.parseInt(inputArr[1]);
        // Check for invalid inputs
        if (inputArr.length > 2 || taskNo > tasks.size() || taskNo <= 0) {
            System.out.println("invalid index");
        } else {
            Task currTask = tasks.get(taskNo - 1);
            if (markStatus.equals("mark")) {
                currTask.markDone();
            } else {
                currTask.markUndone();
            }
            System.out.println(currTask);
        }
    }

    /**
     * Handles the logic for the task manager.
     */
    private static void taskHandler() {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        while (true) {
            // split() can throw PatternSyntaxException
            // Splits the input to retrieve possible commands.
            String[] inputArr = in.split(" ", 0);
            String command = inputArr[0];

            // Break out of loop
            if (in.equals("bye")) {
                break;
            }

            // List out current tasks in the list
            if (in.equals("list")) {
                printTaskList();
            } else if (command.equals("mark")) {
                System.out.println("gz");
                taskMarker(command, inputArr);
            } else if (command.equals("unmark")) {
                System.out.println("rip");
                taskMarker(command, inputArr);
            } else {
                // Add Task to taskList
                // invalid task scenario handled by addTask
                addTask(in, command);
            }
            in = sc.nextLine();
        }
        System.out.println("Have a nice day!");
    }

    public static void main(String[] args) {
        Greet();
        taskHandler();
    }
}
