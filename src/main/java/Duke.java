import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        printGreeting();
        while (true) {
            Scanner input = new Scanner(System.in);
            String inputText = input.nextLine();

            List<String> inputArray = Arrays.asList(inputText.split(" "));

            String keyword = inputArray.get(0);

            switch(keyword) {
                case "bye":
                    printBye();
                    return;
                case "list":
                    printTasks();
                    break;
                case "mark": {
                    if (inputArray.size() != 2) {
                        throw new Exception("Input for mark not correct");
                    }
                    markTask(inputArray.get(1));
                    break;
                }
                case "unmark": {
                    if (inputArray.size() != 2) {
                        throw new Exception("Input for unmark not correct");
                    }
                    unmarkTask(inputArray.get(1));
                    break;
                }
                case "todo": {
                    ToDo newTask = new ToDo(String.join(" ", inputArray.subList(1,inputArray.size())));
                    tasks.add(newTask);

                    printAddTask(newTask);
                    break;
                }
                case "event": {
                    String command = String.join(" ", inputArray.subList(1,inputArray.size()));
                    String[] commandArray = command.split(" /at ");
                    //TODO: check command array is at least length 2
                    Event newTask = new Event(commandArray[0], commandArray[1]);
                    tasks.add(newTask);

                    printAddTask(newTask);
                    break;
                }
                case "deadline": {
                    String command = String.join(" ", inputArray.subList(1,inputArray.size()));
                    String[] commandArray = command.split(" /by ");

                    Deadline newTask = new Deadline(commandArray[0], commandArray[1]);
                    tasks.add(newTask);

                    printAddTask(newTask);
                    break;
                }
                default: {
                    System.out.println("You did not provide a valid command");
                }
            }
        }
    }

    private static void printAddTask(Task task) {
        System.out.println("\n  _______________");
        System.out.println("  Added: " + task);
        System.out.println(String.format("  Now you have %d tasks in the list", tasks.size()));
        System.out.println("  _______________\n");
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
