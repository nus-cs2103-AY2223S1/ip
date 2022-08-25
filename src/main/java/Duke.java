import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
    private static final String filePath = "data/duke.txt";
    private List<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void getData() throws DukeException {
        Scanner sc;
        try {
            sc = new Scanner(new File(filePath));
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found: Unable to retrieve data.");
        }
        sc.useDelimiter("( \\| )|(\\n)");  // split by | or new line
        while (sc.hasNext()) {
            try {
                String type = sc.next(), status = sc.next(), description = sc.next();
                Task task;
                switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, sc.next());
                    break;
                case "E":
                    task = new Event(description, sc.next());
                    break;
                default:
                    throw new DukeException("Invalid task type: %s", type);
                }
                if (status.equals("1")) {
                    task.markAsDone();;
                } else if (!status.equals("0")) {
                    throw new DukeException("Invalid task status: %s", status);
                }
                tasks.add(task);
            } catch (NoSuchElementException e) {
                throw new DukeException("File content is not in the correct format.");
            }
        }
    }

    private void saveData() throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(new File(filePath));
            String content = tasks.stream().map(x -> x.getSaveFormat() + "\n").reduce("", (x,y) -> x + y);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException("IOException: Unable to save data.");
        }
    }

    private void run() {
        print("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        try {
            getData();
        } catch (DukeException e) {
            print(e.getMessage());
        }
        scanner: while (sc.hasNext()) {
            String input = sc.next();
            try {
                switch (input) {
                case "list":
                    String date = sc.nextLine().trim();
                    if (date.isEmpty()) {
                        list();
                    } else {
                        list(date);
                    }
                    break;
                
                case "todo":
                case "deadline":
                case "event":
                    add(input, sc.nextLine().trim());
                    break;
                
                case "sort":
                    sort();
                    break;
                
                case "format":
                    format(sc.nextLine().trim());
                    break;

                case "mark":
                    mark(sc.nextLine().trim());
                    break;

                case "unmark":
                    unmark(sc.nextLine().trim());
                    break;
                
                case "delete":
                    delete(sc.nextLine().trim());
                    break;
    
                case "bye":
                    bye();
                    break scanner;
        
                default:
                    sc.nextLine();
                    throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                print(e.getMessage());
            }
        }
        sc.close();
        try {
            saveData();
        } catch (DukeException e) {
            print(e.getMessage());
        }
    }

    private void print(String s, Object... args) {
        String seperator = "    ____________________________________________________________\n";
        System.out.println(seperator + Stream.of(String.format(s, args).split("\n")).map(x -> "     " + x + "\n").reduce("", (x,y) -> x + y) + seperator);
    }

    private void list() {
        print(IntStream.range(0, tasks.size()).mapToObj(x -> String.format("%d.%s\n", x + 1, tasks.get(x))).reduce("Here are the tasks in your list:\n", (x,y) -> x + y));
    }

    private void list(String date) throws DukeException {
        LocalDate convertedDate;
        try {
            convertedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date format. Please input date in the format dd/MM/yyyy.");
        }
        String content = IntStream.range(0, tasks.size())
            .filter(x -> tasks.get(x) instanceof TimedTask && ((TimedTask) tasks.get(x)).time.toLocalDate().equals(convertedDate))
            .mapToObj(x -> String.format("%d.%s\n", x + 1, tasks.get(x)))
            .reduce("", (x,y) -> x + y);
        print(String.format("Here are the tasks in your list at %s:\n%s", date, content));
    }

    private String[] getTaskDetails(String rawDetails, String delimiter) throws DukeException {
        String[] details = rawDetails.split(delimiter);
        if (details.length != 2 || details[0].isEmpty() || details[1].isEmpty()) {
            throw new DukeException("\u2639 OOPS!!! Please make sure task description and dates are inputted properly!");
        }
        return details;
    }

    private void add(String taskType, String rawDetails) throws DukeException {
        Task task;
        if (rawDetails.isEmpty()) throw new DukeException("\u2639 OOPS!!! The description of a %s cannot be empty.", taskType);
        if (taskType.equals("deadline")) {
            String[] details = getTaskDetails(rawDetails, " /by ");
            task = new Deadline(details[0], details[1]);
        } else if (taskType.equals("event")) {
            String[] details = getTaskDetails(rawDetails, " /at ");
            task = new Event(details[0], details[1]);
        } else {
            task = new Todo(rawDetails);
        }
        tasks.add(task);
        print("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.", tasks.get(tasks.size() - 1), tasks.size());
    }

    private void sort() {
        Collections.sort(tasks);
        list();
    }

    private void format(String format) throws DukeException {
        TimedTask.setFormat(format);
        list();
    }

    private void mark(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            tasks.get(index - 1).markAsDone();
            print("Nice! I've marked this task as done:\n  " + tasks.get(index - 1));
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. Index must be an integer and must not be blank.", input);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.", input, tasks.size());
        }
    }

    private void unmark(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            tasks.get(index - 1).markAsDone();
            print("OK, I've marked this task as not done yet:\n  " + tasks.get(index - 1));
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. Index must be an integer and must not be blank.", input);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.", input, tasks.size());
        }
    }

    private void delete(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            Task removedTask = tasks.remove(index - 1);
            print("Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.", removedTask, tasks.size());
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. Index must be an integer and must not be blank.", input);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Invalid index %s. You only have %d tasks in your list.", input, tasks.size());
        }
    }

    private void bye() {
        print("Bye. Hope to see you again soon!");
    }
}