import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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
 *   deadline. The deadline needs to be a valid date (e.g., 21 Aug 2022, Aug 21 2022 or
 *   2022-08-21), with time optional. If time is provided, it must be provided after the date,
 *   with exactly one space in between.
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
    private static TaskList tasks = new TaskList();
    private static Storage storage;

    static {
        try {
            storage = new Storage("data/tasks.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Parses date in ISO 8601 format or DD MMM YYYY format (e.g. 21 Aug 2022) or MMM DD YYYY
    // format (e.g. Aug 21 2022) and returns a string in DD MMM YYYY format. If date parse fails,
    // throws IllegalArgumentException.
    // Time, if provided, should be given in 12 or 24-hour format, and provided after the date
    // string, which is not in ISO format.
    // This parser is not case-sensitive.
    private static String convertToDate(String date) {
        // Solution adapted from
        // https://nus-cs2103-ay2223s1.github.io/website/schedule/week3/project.html
        LocalDateTime dateObject = null;
        try {
            dateObject = LocalDateTime.parse(date);
        } catch (DateTimeParseException ex) {
            boolean valid = false;
            String[] dateTimeFormats = {
                    "yyyy-MM-dd",
                    "yyyy-MM-dd H:mm",
                    "yyyy-MM-dd h:mm a",
                    "dd MMM yyyy",
                    "dd MMM yyyy H:mm",
                    "dd MMM yyyy h:mm a",
                    "MMM dd yyyy",
                    "MMM dd yyyy H:mm",
                    "MMM dd yyyy h:mm a",
            };
            boolean[] isDateFormats = { true, false, false, true, false, false, true, false, false };
            for (int i = 0; i < dateTimeFormats.length; i++) {
                // Solution below adapted from
                // https://stackoverflow.com/questions/44925840/
                // java-time-format-datetimeparseexception-text-could-not-be-parsed-at-index-3
                DateTimeFormatter dateFormat = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(dateTimeFormats[i])
                        .toFormatter(Locale.ENGLISH);
                try {
                    if (isDateFormats[i]) {
                        // Solution below adapted from
                        // https://stackoverflow.com/questions/27454025/
                        // unable-to-obtain-localdatetime-from-temporalaccessor-when-parsing-localdatetime
                        dateObject = LocalDate.parse(date, dateFormat).atStartOfDay();
                    } else {
                        dateObject = LocalDateTime.parse(date, dateFormat);
                    }
                    valid = true;
                    break;
                } catch (DateTimeParseException ex2) {
                }
            }
            if (!valid) {
                throw new IllegalArgumentException("Please input a valid date, e.g. 21 Aug 2022, " +
                        "Aug 21 2022 or 2022-08-21.");
            }
        }
        return dateObject.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"));
    }

    /**
     * Runs the task tracker.
     *
     * @param args not used for now
     */
    public static void main(String[] args) throws IOException {
        tasks = storage.load();

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
                            deadline = convertToDate(deadline);
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
                        storage.save(tasks);
                        System.out.printf("The following %s task has been added:\n  ", tokens[0]);
                        System.out.println(taskAdded);
                        System.out.printf("The task list now contains %d task(s).\n", tasks.size());
                    }
                } else if (tokens[0].equals("mark")) {
                    int index = Integer.parseInt(tokens[1]);
                    Task task = tasks.get(index);

                    // Solution below adapted from
                    // https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
                    task.markAsDone();
                    storage.save(tasks);

                    System.out.println("The following task has been marked as done");
                    System.out.println(task);
                } else if (tokens[0].equals("unmark")) {
                    int index = Integer.parseInt(tokens[1]);
                    Task task = tasks.get(index);
                    task.markAsUndone();
                    storage.save(tasks);
                    System.out.println("The following task has been marked as undone");
                    System.out.println(task);
                } else if (tokens[0].equals("delete")) {
                    int index = Integer.parseInt(tokens[1]);
                    Task task = tasks.get(index);
                    tasks.delete(index);
                    storage.save(tasks);
                    System.out.println("The following task has been removed:");
                    System.out.println(task);
                    System.out.printf("The task list now contains %d task(s).\n", tasks.size());
                } else if (str.equals("list")) {
                    for (int i = 1; i <= tasks.size(); i++) {
                        Task task = tasks.get(i);
                        System.out.printf("%d.%s\n", i, task);
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
