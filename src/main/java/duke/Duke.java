package duke;

import duke.task.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Duke {
    private static final Path PATH = Paths.get(System.getProperty("user.home"),
            "duke",
            "tasks.txt"
    );
    private static final Duke INSTANCE = Duke.createNewInstance();
    private final TaskList taskList;

    private Duke(TaskList taskList) {
        this.taskList = taskList;
    }

    private static Duke createNewInstance() {
        if (PATH != null && Files.exists(PATH) && Files.isRegularFile(PATH)) {
            try {
                return new Duke(TaskList.readFromFile(PATH));
            } catch (IOException e) {
                System.out.println("Failed to restore task list from " + PATH);
                System.out.println("Creating a new task list instead.");
                return new Duke(TaskList.newEmptyTaskList());
            }
        }
        return new Duke(TaskList.newEmptyTaskList());
    }

    public static Duke getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" +
                "|  _ \\ _   _| | _____ \n" +
                "| | | | | | | |/ / _ \\\n" +
                "| |_| | |_| |   <  __/\n" +
                "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        Duke duke = Duke.getInstance();

        System.out.print("\n> ");
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                boolean isTerminal = duke.executeCommand(input);
                if (isTerminal) {
                    return;
                }
            } catch (DukeException e) {
                System.out.printf("%s.\n", e.getMessage());
            } catch (IOException e) {
                System.out.println(e.toString());
            }
            System.out.print("\n> ");
        }
    }

    private static String[] getArguments(String command) {
        if (command.isBlank()) {
            return new String[0];
        }
        return command.split(" ");
    }

    private static int findArgumentIndex(String[] arguments, String query) {
        return IntStream.range(0, arguments.length)
                .filter(i -> arguments[i].equals(query))
                .findFirst()
                .orElseThrow(() -> new DukeException(String.format(
                        "Missing argument `%s`",
                        query
                )));
    }

    private static String concatenateArguments(String[] arguments, int start, int end) {
        return Arrays.stream(arguments, start, end)
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                )
                .toString();
    }

    private static String concatenateArguments(String[] arguments, int start) {
        return Arrays.stream(arguments, start, arguments.length)
                .map(str -> str + " ")
                .collect(StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                )
                .toString()
                .strip();
    }

    private void printTaskListSize() {
        System.out.printf("You now have %d %s.",
                taskList.size(),
                taskList.size() == 1 ? "task" : "tasks"
        );
    }

    /**
     * Executes a given command assuming all arguments are correct.
     *
     * @param command the command string
     * @return `true` if the command is terminal, `false` otherwise
     */
    private boolean executeCommand(String command) throws IOException {
        String[] arguments = getArguments(command);
        if (arguments.length == 0) {
            System.out.println("Please enter a command.");
            return false;
        }
        switch (arguments[0]) {
            case "mark": {
                int index = Integer.parseInt(arguments[1]) -
                        1; // task indexing is 1-indexed
                Task task = taskList.getTask(index);
                task.markAsDone();
                System.out.println("I've marked this task as done.");
                System.out.println(task);
                break;
            }
            case "unmark": {
                int index = Integer.parseInt(arguments[1]) -
                        1; // task indexing is 1-indexed
                Task task = taskList.getTask(index);
                task.markAsUndone();
                System.out.println("I've unmarked this task as done.");
                System.out.println(task);
                break;
            }
            case "delete": {
                if (arguments.length < 2) {
                    throw new DukeException("Missing task index");
                }
                int index = Integer.parseInt(arguments[1]) -
                        1; // task indexing is 1-indexed
                Task task = taskList.getTask(index);
                taskList.deleteTask(index);
                System.out.println("I've deleted this task.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            case "list": {
                System.out.println("Here are your tasks:");
                System.out.println(this.taskList);
                break;
            }
            case "bye": {
                System.out.println("Bye! Hope to see you again soon!");
                return true;
            }
            case "todo": {
                if (arguments.length < 2) {
                    throw new DukeException("Missing todo description");
                }
                String description = concatenateArguments(arguments, 1);
                Task task = new Todo(description);
                taskList.addTask(task);
                System.out.println("I've added this task.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            case "deadline": {
                if (arguments.length < 2) {
                    throw new DukeException("Missing deadline description");
                }
                // Find the "/by" delimiter to get the two arguments.
                int delimiter = findArgumentIndex(arguments, "/by");
                String description = concatenateArguments(arguments,
                        1,
                        delimiter
                );
                String deadline = concatenateArguments(arguments,
                        delimiter + 1
                );
                // Create and add the task.
                Task task = new Deadline(description, deadline);
                taskList.addTask(task);
                System.out.println("I've added this deadline.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            case "event": {
                if (arguments.length < 2) {
                    throw new DukeException("Missing event description");
                }
                // Find the "/at" delimiter to get the two arguments.
                int delimiter = findArgumentIndex(arguments, "/at");
                String description = concatenateArguments(arguments,
                        1,
                        delimiter
                );
                String datetime = concatenateArguments(arguments,
                        delimiter + 1
                );
                // Create and add the task.
                Task task = new Event(description, datetime);
                taskList.addTask(task);
                System.out.println("I've added this event.");
                System.out.println(task);
                printTaskListSize();
                break;
            }
            default: {
                throw new DukeException("Unknown command");
            }
        }
        taskList.writeToFile(PATH);
        System.out.println();
        return false;
    }
}
