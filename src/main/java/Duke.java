import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        printGreeting();
        Scanner input = new Scanner(System.in);
        while (true) {
            String inputText = input.nextLine();

            List<String> inputArray = Arrays.asList(inputText.split(" "));

            String keyword = inputArray.get(0);

            try {
                switch (keyword) {
                    case "bye":
                        printBye();
                        return;
                    case "list":
                        printTasks();
                        break;
                    case "mark": {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for mark not correct");
                        }
                        markTask(inputArray.get(1));
                        break;
                    }
                    case "unmark": {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for unmark not correct");
                        }
                        unmarkTask(inputArray.get(1));
                        break;
                    }
                    case "todo": {
                        ToDo newTask = new ToDo(String.join(" ", inputArray.subList(1, inputArray.size())));
                        tasks.add(newTask);

                        printAddTask(newTask);
                        break;
                    }
                    case "event": {
                        String command = String.join(" ", inputArray.subList(1, inputArray.size()));
                        String[] commandArray = command.split(" /at ");

                        if (commandArray.length < 2) {
                            throw new DukeException("An event must at least have a description and date!");
                        }

                        Event newTask = new Event(commandArray[0], commandArray[1]);
                        tasks.add(newTask);

                        printAddTask(newTask);
                        break;
                    }
                    case "deadline": {
                        String command = String.join(" ", inputArray.subList(1, inputArray.size()));
                        String[] commandArray = command.split(" /by ");

                        if (commandArray.length < 2) {
                            throw new DukeException("A deadline must at least have a description and date!");
                        }

                        Deadline newTask = new Deadline(commandArray[0], commandArray[1]);
                        tasks.add(newTask);

                        printAddTask(newTask);
                        break;
                    }
                    default: {
                        throw new DukeException("You did not provide a valid command");
                    }
                }
            } catch (DukeException e) {
                System.out.println(e);
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
        System.out.println("Goodbye. Hope to see you again soon");
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
        task.markIsDone();
    }

    private static void unmarkTask(String input) throws Exception {
        Task task = getTask(input);
        task.unmarkIsDone();
    }

    private static Task getTask(String input) throws Exception {
        Task task;

        try {
            int taskNum = Integer.parseInt(input) - 1;
            task = tasks.get(taskNum);
        } catch (RuntimeException e) {
            throw new DukeException("Task number is incorrectly provided");
        }

        return task;
    }
}
