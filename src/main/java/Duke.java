import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Could refactor this out into a TaskList class
    static ArrayList<Task> tasks = new ArrayList<>();

    private enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        EVENT,
        DEADLINE,
    }

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(System.in);

        String home = System.getProperty("user.home");
        Path filepath = Paths.get(home, "Desktop", "ip_save.txt");
        Storage storage = new Storage(filepath.toString());

        printGreeting();

        while (true) {
            String inputText = input.nextLine();

            List<String> inputArray = Arrays.asList(inputText.split(" "));

            Command keyword;
            try {
                try {
                    keyword = Command.valueOf(inputArray.get(0).toUpperCase());
                } catch (Exception e) {
                    throw new DukeException("You did not provide a valid command");
                }

                switch (keyword) {
                    case BYE:
                        printBye();
                        return;
                    case LIST:
                        printTasks();
                        break;
                    case MARK: {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for mark not correct");
                        }
                        markTask(inputArray.get(1));
                        break;
                    }
                    case UNMARK: {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for unmark not correct");
                        }
                        unmarkTask(inputArray.get(1));
                        break;
                    }
                    case DELETE: {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for delete not correct");
                        }
                        deleteTask(inputArray.get(1));
                        break;
                    }
                    case TODO: {
                        ToDo newTask = new ToDo(String.join(" ", inputArray.subList(1, inputArray.size())));
                        tasks.add(newTask);

                        printAddTask(newTask);
                        break;
                    }
                    case EVENT: {
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
                    case DEADLINE: {
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
                storage.save(tasks);
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
        System.out.println("Goodbye. Hope to see you again soon!");
    }

    private static void printTasks() {
        System.out.println("Your tasks:");
        for (Task task : tasks) {
            int taskIndex = tasks.indexOf(task) + 1;
            String taskString = String.format("%d. %s", taskIndex, task);
            System.out.println(taskString);
        }
    }

    private static void markTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        task.markIsDone();
    }

    private static void unmarkTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        task.unmarkIsDone();
    }

    private static void deleteTask(String input) throws Exception {
        int taskNum = Integer.parseInt(input) - 1;
        Task task = getTask(taskNum);
        try {
            tasks.remove(taskNum);

            System.out.println("\n  _______________");
            System.out.println("  Removed: " + task);
            System.out.println(String.format("  Now you have %d tasks in the list", tasks.size()));
            System.out.println("  _______________\n");
        } catch (Exception e) {
            throw new DukeException(String.format("Task number %d not found", taskNum));
        }
    }

    private static Task getTask(int taskNum) throws Exception {
        Task task;

        try {
            task = tasks.get(taskNum);
        } catch (Exception e) {
            throw new DukeException("Task number is incorrectly provided");
        }

        return task;
    }
}
