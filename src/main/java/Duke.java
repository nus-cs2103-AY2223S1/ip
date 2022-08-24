import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static final String BORDER = ">>=========================="
            + "============[**]============="
            + "=========================<<";

    private static ArrayList<Task> taskList = new ArrayList<>();
    private static boolean isRunning = true;

    // encapsulate in a TaskList class later with other methods
    public static void list() {
        if (taskList.size() == 0) {
            System.out.println("  You don't have any tasks. Make yourself useful.");
        } else {
            System.out.println("  *rolls eyes*\n"
                    + "  Do I have to?\n"
                    + "  Fine. Here are your tasks:");
            taskList.forEach(t -> System.out.println("  " + (taskList.indexOf(t) + 1) + ". " + t));
        }
    }

    public static String convertTasksToSaveFormat() {
        StringBuilder sb = new StringBuilder();
        taskList.forEach(t -> sb.append(t.toSaveFormat()).append("\n"));
        return sb.toString();
    }

    public static void saveTasks() {
        try {
            Path dir = Paths.get("..", "data");
            File taskFileDirectory = new File(dir.toString());
            Path path = Paths.get(dir.toString(), "duke.txt");
            File taskFile = new File(path.toString());
            if (Files.exists(dir)) {
                taskFile.createNewFile();
                Files.write(path, convertTasksToSaveFormat().getBytes());
            } else {
                taskFileDirectory.mkdirs();
                taskFile.createNewFile();
                Files.write(path, convertTasksToSaveFormat().getBytes());
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to save tasks.");
            e.printStackTrace();
        }
    }

    public static void loadTasks() {
        try {
            Path dir = Paths.get("..", "data");
            Path path = Paths.get(dir.toString(), "duke.txt");
            if (Files.exists(dir) && Files.exists(path)) {
                List<String> allLines = Files.readAllLines(path);
                for (String line : allLines) {
                    String[] parsedTask = line.split(" \\| ", 4);
                    boolean isDone = parsedTask[1].compareTo("1") == 0;
                    String desc = parsedTask[2];
                    switch (parsedTask[0]) {
                    case "T":
                        ToDo todo = new ToDo(desc, isDone);
                        taskList.add(todo);
                        break;
                    case "D":
                        String[] parsedDateTime = parsedTask[3].split(" ", 2);
                        if (parsedDateTime.length < 2) {
                            LocalDate date = convertStringToDate(parsedTask[3]);
                            Deadline deadline = new Deadline(desc, date, isDone);
                            taskList.add(deadline);
                        } else {
                            LocalDateTime dateTime = convertStringToDateTime(parsedTask[3]);
                            Deadline deadline = new Deadline(desc, dateTime, isDone);
                            taskList.add(deadline);
                        }
                        break;
                    case "E":
                        parsedDateTime = parsedTask[3].split(" ", 2);
                        if (parsedDateTime.length < 2) {
                            LocalDate date = convertStringToDate(parsedTask[3]);
                            Event event = new Event(desc, date, isDone);
                            taskList.add(event);
                        } else {
                            LocalDateTime dateTime = convertStringToDateTime(parsedTask[3]);
                            Event event = new Event(desc, dateTime, isDone);
                            taskList.add(event);
                        }
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error: Unable to load tasks.");
            e.printStackTrace();
        }
    }

    public static String commandGuide(String keyword, Command cmd) {
        String msg = "";
        switch (cmd) {
        case TODO:
            msg = "  Type \"" + keyword + " <description>\" to add a new todo.";
            break;
        case DEADLINE:
            msg = "  Type \"" + keyword + " <description> /by <date> <time>[optional]\" to add a new deadline.";
            break;
        case EVENT:
            msg = "  Type \"" + keyword + " <description> /at <date> <time>[optional]\" to add a new event.";
            break;
        case MARK:
            msg = "  Type \"" + keyword + " <task number>\" to mark a task as complete.";
            break;
        case UNMARK:
            msg = "  Type \"" + keyword + " <task number>\" to mark a task as incomplete.";
            break;
        case DELETE:
            msg = "  Type \"" + keyword + " <task number>\" to delete a task.";
            break;
        }
        return msg;
    }

    // overload method
    public static void addTask(String description) throws MissingDescriptionException {
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        saveTasks();
        System.out.println("  Seriously? Another one?\n" + "  Give me strength...\n"
                + "    " + todo + "\n" + "  You have " + taskList.size() + " task"
                + (taskList.size() > 1 ? "s" : "") + ". Bummer.");
    }

    public static void addTask(String taskType, String description, String dateTimeStr) throws MissingDescriptionException {
        Task task = null;
        String[] parsedDateTime = dateTimeStr.split(" ", 2);
        
        if (parsedDateTime.length < 2) {
            LocalDate date = convertStringToDate(dateTimeStr);
            if (taskType.compareTo("deadline") == 0) {
                task = new Deadline(description, date);
            } else {
                task = new Event(description, date);
            }
        } else {
            LocalDateTime dateTime = convertStringToDateTime(dateTimeStr);
            if (taskType.compareTo("deadline") == 0) {
                task = new Deadline(description, dateTime);
            } else {
                task = new Event(description, dateTime);
            }
        }
        
        taskList.add(task);
        saveTasks();
        System.out.println("  Seriously? Another one?\n" + "  Give me strength...\n"
                + "    " + task + "\n" + "  You have " + taskList.size() + " task"
                + (taskList.size() > 1 ? "s" : "") + ". Bummer.");
    }

    public static void deleteTask(int num) {
        try {
            Task removed = taskList.remove(num - 1);
            saveTasks();
            System.out.println("  Good riddance, I say.\n" + "    " + removed
                + "\n  You have " + taskList.size() + " task"
                    + (taskList.size() == 1 ? "" : "s") + ".");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Impressive. You've figured out how to delete non-existent tasks.\n"
                    + "  Task number " + num + " does not exist.");
        }
    }
    
    public static void deleteAllTasks() {
        taskList.removeAll(taskList);
        saveTasks();
        System.out.println("  All tasks deleted.");
    }

    public static void quit() {
        System.out.println("  With all due disrespect, leave me alone next time.");
        isRunning = false;
    }

    public static void markTask(int num) {
        try {
            taskList.get(num - 1).mark();
            saveTasks();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Brilliant. You've asked me to mark an imaginary task as complete.\n"
                    + "  Task number " + num + " does not exist.");
        }
    }

    public static void unmarkTask(int num) {
        try {
            taskList.get(num - 1).unmark();
            saveTasks();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Brilliant. You've asked me to mark an imaginary task as incomplete.\n"
                    + "  Task number " + num + " does not exist.");
        }
    }

    public static void parseCommand(String input, Scanner sc) throws UnknownCommandException,
            MissingTaskNumberException, MissingDescriptionException, MissingDateException {
        String[] parsed = input.split(" ", 2); // splits string at the first whitespace encountered
        String keyword = parsed[0];
        try {
            if (input.compareTo("bye") == 0) {
                sc.close();
                quit();
            } else if (input.compareTo("delete all") == 0) {
                deleteAllTasks();
            } else if (input.compareTo("list") == 0) {
                list();
            } else if (keyword.compareTo("mark") == 0) {
                if (parsed.length < 2 || parsed[1].isBlank()) {
                    throw new MissingTaskNumberException(commandGuide(keyword, Command.MARK));
                }
                int num = Integer.parseInt(parsed[1]);
                markTask(num);
            } else if (keyword.compareTo("unmark") == 0) {
                if (parsed.length < 2 || parsed[1].isBlank()) {
                    // replace messages using enums
                    throw new MissingTaskNumberException(commandGuide(keyword, Command.UNMARK));
                }
                int num = Integer.parseInt(parsed[1]);
                unmarkTask(num);
            } else if (keyword.compareTo("delete") == 0) {
                if (parsed.length < 2 || parsed[1].isBlank()) {
                    throw new MissingTaskNumberException(commandGuide(keyword, Command.DELETE));
                }
                int num = Integer.parseInt(parsed[1]);
                deleteTask(num);
                // repetitive; try to abstract away with another method
            } else if (keyword.compareTo("todo") == 0) {
                if (parsed.length < 2 || parsed[1].isBlank()) {
                    // replace messages using enums
                    throw new MissingDescriptionException(commandGuide(keyword, Command.TODO));
                }
                addTask(parsed[1]);
            } else if (keyword.compareTo("deadline") == 0) {
                if (parsed.length < 2 || parsed[1].isBlank()) {
                    throw new MissingDescriptionException(commandGuide(keyword, Command.DEADLINE));
                } else {
                    String[] parsedTask = parsed[1].split(" /by ", 2);
                    if (parsedTask[0].startsWith("/by")) {
                        throw new MissingDescriptionException(commandGuide(keyword, Command.DEADLINE));
                    } else if (parsedTask.length < 2) {
                        throw new MissingDateException(commandGuide(keyword, Command.DEADLINE));
                    } else {
                        addTask(keyword, parsedTask[0], parsedTask[1]);
                    }
                }
            } else if (keyword.compareTo("event") == 0) {
                if (parsed.length < 2 || parsed[1].isBlank()) {
                    throw new MissingDescriptionException(commandGuide(keyword, Command.EVENT));
                } else {
                    String[] parsedTask = parsed[1].split(" /at ", 2);
                    if (parsedTask[0].startsWith("/at")) {
                        throw new MissingDescriptionException(commandGuide(keyword, Command.EVENT));
                    } else if (parsedTask.length < 2) {
                        throw new MissingDateException(commandGuide(keyword, Command.EVENT));
                    } else {
                        addTask(keyword, parsedTask[0], parsedTask[1]);
                    }
                }
            } else {
                throw new UnknownCommandException();
            }
        } catch (NumberFormatException e) {
            System.out.println("  Do you need me to teach you what a number is?\n"
                    + "  '" + parsed[1] + "' is not a number.");
        }
    }
    
    public static LocalDateTime convertStringToDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    public static LocalDate convertStringToDate(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("d/M/yyyy"));
    }

    public static void printResponse(String input, Scanner sc) {
        System.out.println(BORDER);
        try {
            parseCommand(input, sc);
        } catch (MissingTaskNumberException e) {
            System.out.println("  Enter a task number, nitwit.\n" + e.getMessage());
        } catch (MissingDescriptionException e) {
            System.out.println("  It seems you've invented a way to do nothing. Typical...\n"
                    + e.getMessage());
        } catch (MissingDateException e) {
            System.out.println("  You do realise deadlines and events usually have a time or date, right?\n"
                    + e.getMessage());
        } catch (UnknownCommandException e) {
            System.out.println("  If you want my help, the least you could do is say something I understand.");
        } catch (DateTimeParseException e) {
            System.out.println("  Invalid date or time.\n  Please enter a date and time with either of the following "
                    + "formats:\n  'dd/mm/yyyy HHMM' or 'dd/mm/yyyy'.");
        }
        System.out.println(BORDER);
    }

    public static void main(String[] args) {
        loadTasks();
        String mort = "                                 .---.        .-----------\n"
                + "                                /     \\  __  /    ------\n"
                + "                               / /     \\(  )/    -----\n"
                + "                              //////   ' \\/ `   ---\n"
                + "                             //// / // :    : ---\n"
                + "                            // /   /  /`    '--\n"
                + "                           //          //..\\\\\n"
                + ">>====================================UU[**]UU====================================<<\n"
                + "                                      '//||\\\\`\n"
                + "                                        ''``";

        System.out.println(mort);
        System.out.println("  Oh, it's you again...\n  Mort, begrudgingly at your service.");
        Scanner sc = new Scanner(System.in);
        System.out.println("  Hmph, what do you want now?\n" + BORDER);

        while (isRunning) {
            System.out.println();
            String input = sc.nextLine();
            System.out.println();
            printResponse(input, sc);
        }
    }
}
