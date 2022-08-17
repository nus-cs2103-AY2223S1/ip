import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        printGreeting();
        while (true) {
            Scanner input = new Scanner(System.in);
            String inputText = input.nextLine();

            String[] inputArray = inputText.split(" ");

            String keyword = inputArray[0];

            switch(keyword) {
                case "bye":
                    printBye();
                    return;
                case "list":
                    printTasks();
                    break;
                case "mark": {
                    if (inputArray.length != 2) {
                        throw new Exception("Input for mark not correct");
                    }
                    markTask(inputArray[1]);
                    break;
                }
                case "unmark": {
                    if (inputArray.length != 2) {
                        throw new Exception("Input for unmark not correct");
                    }
                    unmarkTask(inputArray[1]);
                    break;
                }
                default: {
                    Task newTask = new Task(inputText);
                    tasks.add(newTask);

                    System.out.println("\n  _______________");
                    System.out.println("  Added: " + inputText);
                    System.out.println("  _______________\n");
                }
            }
        }
    }

    private static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    private static void printBye() {
        System.out.println("Goodbye. Hope to see you again soon 😈 \n");
    }

    private static void printTasks() {
        System.out.println("Your tasks:");
        for (Task task: tasks) {
            int taskIndex = tasks.indexOf(task) + 1;
            String taskString = String.format("%d. %s", taskIndex, task);
            System.out.println(taskString);
        }
    }

    private static void markTask(String input) throws Exception {
        Task task = getTask(input);

        if (task == null) {
            throw new Exception();
        }

        task.markIsDone();
    }

    private static void unmarkTask(String input) throws Exception {
        Task task = getTask(input);

        if (task == null) {
            throw new Exception();
        }

        task.unmarkIsDone();
    }

    private static Task getTask(String input) throws Exception {
        Task task;

        try {
            int taskNum = Integer.parseInt(input) - 1;
            task = tasks.get(taskNum);
        } catch (RuntimeException e) {
            System.out.println("Task number is incorrectly provided");
            throw new Exception();
        }

        return task;
    }
}
