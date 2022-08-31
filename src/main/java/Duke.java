import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The main class for the Duke program
 */
public class Duke {
    private static final String DATA_FILE_PATH = "data";
    private static final String DATA_FILE_NAME = "duke.txt";

    private static String botName = "Duke";
    private static int lineLength = 80;
    private static List<Task> tasks = new ArrayList<>();
    private static boolean isRunning;
    private static FileWriter dataFileWriter;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try {
            loadData();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error: Failed to access data");
            return;
        }

        System.out.printf("Hello, I'm %s\n", botName);
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        isRunning = true;
        while (isRunning) {
            generateLine(lineLength);
            String input = scanner.nextLine();
            generateLine(lineLength);

            try {
                parseInput(input);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Goodbye.");
        try {
            for (Task task: tasks) {
                dataFileWriter.write(task.toSaveFormatString() + "\n");
            }

            dataFileWriter.close();
        } catch (IOException e) {
            System.out.println("Error: Failed to save tasks");
        }
    }

    /**
     * Attempts to load saved tasks from the hard disk.
     * Creates the save file and directory if missing.
     *
     * @throws IOException
     */
    private static void loadData() throws IOException {
        Path parentDir = Paths.get(DATA_FILE_PATH);
        if (!Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }

        File dataFile = new File(Paths.get(parentDir.toString(), DATA_FILE_NAME).toString());
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        Scanner scanner = new Scanner(dataFile);
        int taskCount = 0;
        while (scanner.hasNextLine()) {
            String saveFormatString = scanner.nextLine();
            String[] args = saveFormatString.split("\\|", 4);
            if (args.length < 3) {
                System.out.println("Invalid task save string, task not added");
                continue;
            }

            String taskType = args[0].strip();
            boolean marked = args[1].strip() == "1" ? true : false;
            String taskName = args[2].strip();
            if (taskName == "") {
                System.out.println("Invalid task save string, task not added");
                continue;
            }

            LocalDate date = null;
            if (args.length >= 4) {
                try {
                    date = LocalDate.parse(args[3].strip());
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date in task save string, task not added");
                }
            }

            switch (taskType) {
            case "T":
                tasks.add(new ToDo(taskName, marked));
                taskCount++;
                break;
            case "D":
                if (date != null) {
                    tasks.add(new Deadline(taskName, marked, date));
                    taskCount++;
                }
                break;
            case "E":
                if (date != null) {
                    tasks.add(new Event(taskName, marked, date));
                    taskCount++;
                }
                break;
            }
        }
        scanner.close();

        System.out.printf("%d tasks loaded from save\n", taskCount);

        dataFileWriter = new FileWriter(dataFile);
    }

    /**
     * Parses an input string and calls the relevant method (if any)
     *
     * @param input - the input string to be parsed
     */
    private static void parseInput(String input) throws DukeException {
        String[] words = input.toLowerCase().split(" ", 2);
        String command = words[0];
        String args = "";
        if (words.length > 1) {
            args = words[1];
        }

        switch (command) {
        case "bye":
            isRunning = false;
            break;
        case "list":
            displayTasks();
            break;
        case "mark":
            markTask(true, args);
            break;
        case "unmark":
            markTask(false, args);
            break;
        case "todo":
            addToDo(args);
            break;
        case "deadline":
            addDeadline(args);
            break;
        case "event":
            addEvent(args);
            break;
        case "delete":
            deleteTask(args);
            break;
        default:
            throw new DukeException("Command not recognised");
        }
    }

    /**
     * Prints a line of a specified character length
     *
     * @param length - the character length of the line to print
     */
    private static void generateLine(int length) {
        System.out.println(String.format("%" + length + "s", "").replace(" ", "-"));
    }

    /**
     * Adds the given task to the list
     *
     * @param task - the task to add to the list
     */
    private static void addToList(Task task) {
        tasks.add(task);
        System.out.printf("Task added: %s\n", task);
        System.out.printf("You now have %d task(s) in the list\n", tasks.size());
    }

    /**
     * Creates a ToDo task from the given argument string
     *
     * @param args - the argument string to be parsed
     */
    private static void addToDo(String args) throws DukeException {
        String name = args.strip();
        if (name == "") {
            throw new DukeException("Failed to create todo: No task name given");
        }

        addToList(new ToDo(name, false));
    }

    /**
     * Creates a Deadline task from the given argument string
     *
     * @param args - the argument string to be parsed
     */
    private static void addDeadline(String args) throws DukeException {
        String[] argsArr = args.split(" /by ", 2);
        if (argsArr.length < 2) {
            throw new DukeException("Failed to create deadline: Invalid number of arguments");
        }

        String name = argsArr[0].strip();
        String dateStr = argsArr[1].strip();

        if (name == "") {
            throw new DukeException("Failed to create deadline: No task name given");
        }

        if (dateStr == "") {
            throw new DukeException("Failed to create deadline: No date given");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to create deadline: Invalid date given");
        }

        addToList(new Deadline(name, false, date));
    }

    /**
     * Creates an Event task from the given argument string
     *
     * @param args - the argument string to be parsed
     */
    private static void addEvent(String args) throws DukeException {
        String[] argsArr = args.split(" /at ", 2);
        if (argsArr.length < 2) {
            throw new DukeException("Failed to create event: Invalid number of arguments");
        }

        String name = argsArr[0].strip();
        String dateStr = argsArr[1].strip();

        if (name == "") {
            throw new DukeException("Failed to create event: No task name given");
        }

        if (dateStr == "") {
            throw new DukeException("Failed to create event: No date given");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            throw new DukeException("Failed to create event: Invalid date given");
        }

        addToList(new Deadline(name, false, date));
    }

    /**
     * Displays all tasks in the list
     */
    private static void displayTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i));
        }

        System.out.printf("You have %d task(s) in your list\n", tasks.size());
    }

    /**
     * Marks or unmarks a task based on the given task index string
     *
     * @param isMarked - if true, attempt to mark the task, otherwise attempt to unmark
     * @param indexString - the index of the task to mark
     */
    private static void markTask(boolean isMarked, String indexString) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(indexString.strip());
        } catch (NumberFormatException e) {
            throw new DukeException("Mark failed, invalid index");
        }
        index--;

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Mark failed, index out of range");
        }

        Task task = tasks.get(index);
        if (isMarked) {
            task.mark();
            System.out.printf("Task marked as done: %s\n", task);
        } else {
            task.unmark();
            System.out.printf("Task marked as not done: %s\n", task);
        }
    }

    /**
     * Deletes a task based on the given task index string
     *
     * @param indexString - the index of the task to delete
     * @throws DukeException
     */
    private static void deleteTask(String indexString) throws DukeException {
        int index;
        try {
            index = Integer.parseInt(indexString.strip());
        } catch (NumberFormatException e) {
            throw new DukeException("Delete failed, invalid index");
        }
        index--;

        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Delete failed, index out of range");
        }

        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.printf("Task deleted: %s\n", task);
        System.out.printf("You now have %d task(s) in the list\n", tasks.size());
    }
}
