import TaskTypes.Deadline;
import TaskTypes.Event;
import TaskTypes.ToDo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    // Could refactor this out into a TaskList class
    private TaskList tasklist;

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
        Duke duke = new Duke();
        duke.run();
    }

    private void run() throws Exception {
        Scanner input = new Scanner(System.in);

        String home = System.getProperty("user.home");
        Path filepath = Paths.get(home, "Desktop", "duke.txt");
        Storage storage = new Storage(filepath.toString());

        tasklist = new TaskList(storage.load());
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
                        tasklist.printTasks();
                        break;
                    case MARK: {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for mark not correct");
                        }
                        tasklist.markTask(inputArray.get(1));
                        break;
                    }
                    case UNMARK: {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for unmark not correct");
                        }
                        tasklist.unmarkTask(inputArray.get(1));
                        break;
                    }
                    case DELETE: {
                        if (inputArray.size() != 2) {
                            throw new DukeException("Input for delete not correct");
                        }
                        tasklist.deleteTask(inputArray.get(1));
                        break;
                    }
                    case TODO: {
                        ToDo newTask = new ToDo(String.join(" ", inputArray.subList(1, inputArray.size())));
                        tasklist.addTask(newTask);
                        break;
                    }
                    case EVENT: {
                        String command = String.join(" ", inputArray.subList(1, inputArray.size()));
                        String[] commandArray = command.split(" /at ");

                        String dateString = commandArray[1];

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

                        if (commandArray.length < 2) {
                            throw new DukeException("An event must at least have a description and date!");
                        }

                        Event newTask = new Event(commandArray[0], dateTime);
                        tasklist.addTask(newTask);
                        break;
                    }
                    case DEADLINE: {
                        String command = String.join(" ", inputArray.subList(1, inputArray.size()));
                        String[] commandArray = command.split(" /by ");
                        // must check if /by is given

                        String dateString = commandArray[1];

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime dateTime = LocalDateTime.parse(dateString, formatter);

                        if (commandArray.length < 2) {
                            throw new DukeException("A deadline must at least have a description and date!");
                        }

                        Deadline newTask = new Deadline(commandArray[0], dateTime);
                        tasklist.addTask(newTask);
                        break;
                    }
                    default: {
                        throw new DukeException("You did not provide a valid command");
                    }
                }
                storage.save(tasklist.getTasks());

            } catch (Exception e) {
                System.out.println(new DukeException(e.getMessage()));
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
        System.out.println("Goodbye. Hope to see you again soon!");
    }
}
