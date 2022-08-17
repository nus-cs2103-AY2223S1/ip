import java.io.IOException;
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
        if (!tasks.isEmpty()) {
            for (int i = 1; i <= tasks.size(); i++) {
                System.out.println(i + ". " + tasks.get(i - 1).toString());
            }
            System.out.println("");
        } else {
            System.out.println("No tasks have been added!\n");
        }
    }

    /**
     * Handles the addition of tasks.
     * @param type type of task added.
     * @param description description of the task.
     * @throws DukeException
     */
    private static void addTask(String type, String description) throws DukeException{
        Task task;
        if (type.equals("todo") || type.equals("deadline") || type.equals("event")) {
            if (type.equals("todo")) {
                task = new ToDo(description);
            } else if (type.equals("deadline")) {
                task = new Deadline(description);
            } else {
                task = new Event(description);
            }
            System.out.println("Added: " + task.toString() + "\n");
            tasks.add(task);
        } else {
            throw new DukeException("Invalid Task, please prefix your task!\n");
        }
    }

    /**
     * Marks/Unmarks Tasks.
     * @param markStatus input to mark/unmark a task.
     * @param inputArr input of the user.
     */
    private static void taskMarker(String markStatus, String[] inputArr) {
        try {
            int taskNo = Integer.parseInt(inputArr[1]);
            Task currTask = tasks.get(taskNo - 1);
                if (markStatus.equals("mark")) {
                    currTask.markDone();

                    System.out.println("Task successfully marked!");
                } else {
                    currTask.markUndone();
                    System.out.println("Task successfully unmarked!");
                }
                System.out.println(currTask + "\n");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println("Invalid input, please input an available integer index!\n");
        }
    }

    /**
     * Handles the logic for the task manager.
     */
    private static void taskHandler() {
        Scanner sc = new Scanner(System.in);
        String in = sc.nextLine();

        while (true) {
            // Splits the input to retrieve possible commands.
            String[] inputArr = in.split(" ", 2);
            String command = inputArr[0];

            // Break out of loop
            if (in.equals("bye")) {
                break;
            }

            // List out current tasks in the list
            if (in.equals("list")) {
                printTaskList();
            } else if (command.equals("mark") || (command.equals("unmark"))) {
                taskMarker(command, inputArr);
            } else {
                try {
                    addTask(command, inputArr[1]);
                } catch (DukeException e) {
                    System.out.println(e);
                } catch (IndexOutOfBoundsException p) {
                    System.out.println("Please add a description to your task!\n");
                }
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
