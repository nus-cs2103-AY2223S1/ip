import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String minorIndentation = "  ";
    private static final String indentation = "    ";
    private static final String horizontalLine = indentation + "____________________________________________________________";

    /**
     * Load tasks from file.
     *
     * @return tasks The tasks loaded from the file.
     */
    private static ArrayList<Task> loadTasks() throws DukeException {
        Path directoryPath = Paths.get("data");
        Path filePath = Paths.get("data/tasks");
        File directory = new File(directoryPath.toUri());
        // Create directory if it does not exist.
        // noinspection ResultOfMethodCallIgnored because not only making use of the side effect
        directory.mkdir();
        File file = new File(filePath.toUri());

        ArrayList<Task> tasks = new ArrayList<>();
        try {
            // Create file if it does not exist.
            if (file.createNewFile()) {
                // If file did not exist before this, there are no tasks.
                return tasks;
            }
        } catch (IOException e) {
            throw DukeException.badData;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(" \\| ");
                Task task;
                switch (split[0]) {
                    case "T":
                        task = Todo.create(split[1], split[2]);
                        break;
                    case "D":
                        task = Deadline.create(split[1], split[2], split[3]);
                        break;
                    case "E":
                        task = Event.create(split[1], split[2], split[3]);
                        break;
                    default:
                        throw DukeException.badData;
                }

                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            // Should not happen because file is created beforehand.
            return tasks;
        }

        return tasks;
    }

    /**
     * Save tasks to file.
     *
     * @param tasks The tasks to be saved to the file.
     */
    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        Path filePath = Paths.get("data/tasks");
        File file = new File(filePath.toUri());
        FileWriter fw = new FileWriter(file);

        for (Task task : tasks) {
            String line = task.getFileFormat();
            fw.write(line + "\n");
        }

        fw.close();
    }

    private static void list(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf(indentation + "%d: %s\n", i + 1, tasks.get(i));
        }
    }

    private static void mark(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw DukeException.noIndex;
        }
        String indexInput = split[1];
        int i = Integer.parseInt(indexInput);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.get(i - 1);
        task.markAsDone();

        System.out.printf(indentation + "Marked task %d as done!\n", i);
        System.out.println(indentation + minorIndentation + task);
    }

    private static void unmark(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw DukeException.noIndex;
        }
        String indexInput = split[1];
        int i = Integer.parseInt(indexInput);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.get(i - 1);
        task.markAsUndone();

        System.out.printf(indentation + "Marked task %d as not done!\n", i);
        System.out.println(indentation + minorIndentation + task);
    }

    private static void delete(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw DukeException.noIndex;
        }
        String indexInput = split[1];
        int i = Integer.parseInt(indexInput);
        if (i <= 0 || i > tasks.size()) {
            throw DukeException.invalidIndex;
        }
        // Subtract 1 to account for 0-index data structure.
        Task task = tasks.get(i - 1);
        tasks.remove(i - 1);
        int numberOfTasks = tasks.size();

        System.out.println(indentation + "Removing this todo!");
        System.out.println(indentation + minorIndentation + task);
        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
    }

    private static void todo(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw Todo.emptyDescription;
        }
        String description = split[1];
        Task task = Todo.create(description);
        tasks.add(task);
        int numberOfTasks = tasks.size();

        System.out.println(indentation + "Added this todo!");
        System.out.println(indentation + minorIndentation + task);
        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
    }

    private static void deadline(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw Deadline.emptyDescription;
        }
        String descAndDate = split[1];
        Task task = Deadline.create(descAndDate);
        tasks.add(task);
        int numberOfTasks = tasks.size();

        System.out.println(indentation + "Added this deadline!");
        System.out.println(indentation + minorIndentation + task);
        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
    }

    private static void event(ArrayList<Task> tasks, String[] split) throws DukeException {
        if (split.length != 2) {
            throw Event.emptyDescription;
        }
        String descAndDate = split[1];
        Task task = Event.create(descAndDate);
        tasks.add(task);
        int numberOfTasks = tasks.size();

        System.out.println(indentation + "Added this event!");
        System.out.println(indentation + minorIndentation + task);
        System.out.printf(indentation + "Now you have %d tasks.\n", numberOfTasks);
    }

    public static void main(String[] args) {
        System.out.println(horizontalLine);
        String logo = indentation + "____        _        \n"
                + indentation + "|  _ \\ _   _| | _____ \n"
                + indentation + "| | | | | | | |/ / _ \\\n"
                + indentation + "| |_| | |_| |   <  __/\n"
                + indentation + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(indentation + "Hello I'm\n" + logo);
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks;
        try {
            tasks = loadTasks();
        } catch (DukeException e) {
            System.out.println(indentation + e.getMessage());
            // Load with empty list instead.
            tasks = new ArrayList<>();
        }

        scanLoop:
        while (scanner.hasNext()) {
            try {
                String input = scanner.nextLine();

                String[] split = input.split(" ", 2);
                String command = split[0];

                System.out.println(horizontalLine);

                // Handle the various commands.
                switch (command) {
                    case "bye":
                        // Stops the application, by breaking out of the scan loop.
                        break scanLoop;
                    case "list":
                        Duke.list(tasks);
                        break;
                    case "mark": {
                        Duke.mark(tasks, split);
                        saveTasks(tasks);
                        break;
                    }
                    case "unmark": {
                        Duke.unmark(tasks, split);
                        saveTasks(tasks);
                        break;
                    }
                    case "delete": {
                        Duke.delete(tasks, split);
                        saveTasks(tasks);
                        break;
                    }
                    case "todo": {
                        Duke.todo(tasks, split);
                        saveTasks(tasks);
                        break;
                    }
                    case "deadline": {
                        Duke.deadline(tasks, split);
                        saveTasks(tasks);
                        break;
                    }
                    case "event": {
                        Duke.event(tasks, split);
                        saveTasks(tasks);
                        break;
                    }
                    default:
                        throw DukeException.unknownCommand;
                }

                System.out.println(horizontalLine);
            } catch (DukeException | IOException e) {
                System.out.println(indentation + e.getMessage());
                System.out.println(horizontalLine);
            } catch (NumberFormatException e) {
                // Handles case where user inputs an invalid number.
                System.out.println(indentation + "Invalid number!");
                System.out.println(horizontalLine);
            }
        }

        System.out.println(indentation + "Bye, hope to see you soon!");
        System.out.println(horizontalLine);

        // Close scanner.
        scanner.close();
    }
}
