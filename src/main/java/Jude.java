import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Jude is a task tracker which appears like a command line interface.
 * When > shows up, you can type a command.
 *
 * There are three types of tasks, namely the todo, deadline and event.
 * Todo tasks are tasks without an associated date/time.
 * Deadline tasks have a specific deadline by which the task must be completed.
 * Event tasks have a start time and an end time.
 *
 * Here are the list of commands:
 * todo (description) - adds a todo task with some description
 * deadline (description) /by (deadline) - adds a deadline task with the specified description and
 *   deadline
 * event (description) /at (daterange) - adds an event task with start time and end time as part of
 *   daterange parameter
 * list - lists all tasks
 * mark - mark the task with a specified index (from list command) as done
 *   e.g. mark 2 marks second task as done
 * unmark - mark the task with a specified index (from list command) as undone,
 *   e.g. unmark 2 marks second task as undone
 * delete - delete the task corresponding to a specified index (from list command)
 *   e.g. delete 2 deletes second task
 * bye - exits the program
 *
 * If the command does not have these prefixes, an error will be thrown saying that the bot does
 * not understand.
 */
public class Jude {
    private static List<Task> tasks = new ArrayList<>();

    /*
     * Loads file in order to load the tasks
     * The format is as follows (in separate lines, no extra newlines in between):
     * - typeOfTask (which can be 'T', 'D' or 'E'), representing todo, deadline and event tasks
     *   respectively.
     * - Name of task.
     * - Whether the task is marked as done, 1 if so and 0 otherwise.
     * - Any dates which may be required by the type. For events, the start date is stored on top
     *   of the end date.
     *
     * In between two tasks, there can be extra newlines.
     */
    private static void loadFile(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        tasks = new ArrayList<Task>();
        while (sc.hasNextLine()) {
            String taskType = sc.nextLine();
            if (taskType.isBlank()) {
                // ignore blank lines in between tasks
                continue;
            }

            String taskName = sc.nextLine();
            String done = sc.nextLine();
            boolean isDone = Integer.parseInt(done) == 1;

            Task task = null;
            if (taskType.equals("T")) {
                task = new Todo(taskName, isDone);
            } else if (taskType.equals("D")) {
                String deadline = sc.nextLine();
                task = new Deadline(taskName, isDone, deadline);
            } else if (taskType.equals("E")){
                String eventTime = sc.nextLine();
                task = new Event(taskName, isDone, eventTime);
            } else {
                throw new RuntimeException("Jude cannot understand the input file.");
            }
            tasks.add(task);
        }
    }

    private static void saveFile(File file) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            fw.write(task.toFileSaveString());
        }
        fw.close();
    }

    // Checks index to ensure index not out of bounds.
    private static void checkIndex(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("Invalid index");
        }
    }

    /**
     * Runs the task tracker.
     *
     * @param args not used for now
     */
    public static void main(String[] args) throws IOException {
        // Solution below adapted from
        // https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
        Files.createDirectories(Paths.get("data"));
        File file = new File("data/tasks.txt");

        // Solution below adapted from
        // https://www.w3schools.com/java/java_files_create.asp
        file.createNewFile();
        loadFile(file);

        System.out.println("Hello! I'm Jude.");
        System.out.println("What can I do for you?");

        // Solution below adapted from
        // https://stackoverflow.com/questions/5287538/how-to-get-the-user-input-in-java
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.print("> ");
            String str = sc.nextLine();
            String[] tokens = str.split(" ", 2);
            try {
                if (tokens[0].equals("todo") || tokens[0].equals("deadline") ||
                        tokens[0].equals("event")) {
                    Task taskAdded = null;
                    if (tokens.length == 1 || tokens[1].isBlank()) {
                        throw new IllegalArgumentException(
                                String.format("Description of %s cannot be empty.", tokens[0]));
                    } else if (tokens[0].equals("todo")) {
                        String description = tokens[1];
                        taskAdded = new Todo(description, false);
                    } else if (tokens[0].equals("deadline")) {
                        String remText = tokens[1];
                        String[] remTextTokens = "  ".concat(remText).split(" /by ", 2);
                        if (remTextTokens.length == 2) {
                            String description = remTextTokens[0].trim();
                            String deadline = remTextTokens[1].trim();
                            if (description.isBlank() || description.isEmpty()) {
                                throw new IllegalArgumentException(
                                        "Description of deadline task cannot be empty.");
                            } else if (deadline.isBlank()) {
                                throw new IllegalArgumentException("A deadline task must have a " +
                                        "deadline.");
                            }
                            taskAdded = new Deadline(description, false, deadline);
                        } else {
                            throw new IllegalArgumentException("A deadline task must have a " +
                                    "deadline.");
                        }
                    } else if (tokens[0].equals("event")) {
                        String remText = tokens[1];
                        String[] remTextTokens = "  ".concat(remText).split(" /at ", 2);
                        if (remTextTokens.length == 2) {
                            String description = remTextTokens[0].strip();
                            String when = remTextTokens[1].strip();
                            if (description.isBlank()) {
                                throw new IllegalArgumentException(
                                        "Description of event task cannot be empty.");
                            } else if (when.isBlank()) {
                                throw new IllegalArgumentException("An event task must have a " +
                                        "time at which the event takes place.");
                            }
                            taskAdded = new Event(description, false, when);
                        } else {
                            throw new IllegalArgumentException("An event task must have a time at " +
                                    "which the event takes place.");
                        }
                    }

                    if (taskAdded != null) {
                        tasks.add(taskAdded);
                        saveFile(file);
                        System.out.printf("The following %s task has been added:\n  ", tokens[0]);
                        System.out.println(taskAdded);
                        System.out.printf("The task list now contains %d task(s).\n", tasks.size());
                    }
                } else if (tokens[0].equals("mark")) {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    checkIndex(index);
                    Task task = tasks.get(index);

                    // Solution below adapted from
                    // https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
                    task.markAsDone();
                    saveFile(file);

                    System.out.println("The following task has been marked as done");
                    System.out.println(task);
                } else if (tokens[0].equals("unmark")) {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    checkIndex(index);
                    Task task = tasks.get(index);
                    task.markAsUndone();
                    saveFile(file);
                    System.out.println("The following task has been marked as undone");
                    System.out.println(task);
                } else if (tokens[0].equals("delete")) {
                    int index = Integer.parseInt(tokens[1]) - 1;
                    checkIndex(index);
                    Task task = tasks.get(index);
                    tasks.remove(index);
                    saveFile(file);
                    System.out.println("The following task has been removed:");
                    System.out.println(task);
                    System.out.printf("The task list now contains %d task(s).\n", tasks.size());
                } else if (str.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.printf("%d.%s\n", i + 1, task);
                    }
                } else if (str.equals("bye")) {
                    System.out.println("Goodbye! Have a nice day!");
                    break;
                } else {
                    System.out.println("Sorry, I don't understand what this means!");
                }
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
