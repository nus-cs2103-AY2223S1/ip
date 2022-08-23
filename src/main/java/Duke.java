import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String BORDER = ">>========================="
            + "===========[**]============"
            + "========================<<";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static boolean isRunning = true;

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

    public static String commandGuide(String keyword, Command cmd) {
        String msg = "";
        switch (cmd) {
            case TODO:
                msg = "  Type \"" + keyword + " <description>\" to add a new todo.";
                break;
            case DEADLINE:
                msg = "  Type \"" + keyword + " <description> /by <time>\" to add a new deadline.";
                break;
            case EVENT:
                msg = "  Type \"" + keyword + " <description> /at <time>\" to add a new event.";
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
        System.out.println("  Seriously? Another one?\n" + "  Give me strength...\n"
                + "    " + todo + "\n" + "  You have " + taskList.size() + " task"
                + (taskList.size() > 1 ? "s" : "") + ". Bummer.");
    }

    public static void addTask(String taskType, String description, String time) throws MissingDescriptionException, MissingTimeException {
        Task task = null;
        if (taskType.compareTo("deadline") == 0) {
            task = new Deadline(description, time);
        } else {
            task = new Event(description, time);
        }
        taskList.add(task);
        System.out.println("  Seriously? Another one?\n" + "  Give me strength...\n"
                + "    " + task + "\n" + "  You have " + taskList.size() + " task"
                + (taskList.size() > 1 ? "s" : "") + ". Bummer.");
    }

    public static void deleteTask(int num) {
        try {
            Task removed = taskList.remove(num - 1);
            System.out.println("  Good riddance, I say.\n" + "    " + removed
                + "\n  You have " + taskList.size() + " task"
                    + (taskList.size() == 1 ? "" : "s") + ".");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Impressive. You've figured out how to delete non-existent tasks.\n"
                    + "  Task number " + num + " does not exist.");
        }
    }

    public static void quit() {
        System.out.println("  With all due disrespect, leave me alone next time.");
        isRunning = false;
    }

    public static void markTask(int num) {
        try {
            taskList.get(num - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Brilliant. You've asked me to mark an imaginary task as complete.\n"
                    + "  Task number " + num + " does not exist.");
        }
    }

    public static void unmarkTask(int num) {
        try {
            taskList.get(num - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("  Brilliant. You've asked me to mark an imaginary task as incomplete.\n"
                    + "  Task number " + num + " does not exist.");
        }
    }

    // refactor parsing of event and deadline commands
    public static void parseCommand(String input, Scanner sc) throws UnknownCommandException,
            MissingTaskNumberException, MissingDescriptionException, MissingTimeException {
        String[] parsed = input.split(" ", 2); // splits string at the first whitespace encountered
        String keyword = parsed[0];
        try {
            if (input.compareTo("bye") == 0) {
                sc.close();
                quit();
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
                        throw new MissingTimeException(commandGuide(keyword, Command.DEADLINE));
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
                        throw new MissingTimeException(commandGuide(keyword, Command.EVENT));
                    } else {
                        addTask(keyword, parsedTask[0], parsedTask[1]);
                    }
                }
            } else {
                throw new UnknownCommandException();
            }
        } catch (NumberFormatException e) {
            System.out.println("  Do you need me to teach you what a number is?\n"
                    + "  " + parsed[1] + " is not a number.");
        }
    }
    
    // accept in formats: dd/mm/yyyy tttt, am pm, yyy-mm-dd, single digit months and days, MMM dd yyy (Oct 15 2019)
    // accept without time
    public static LocalDateTime parseDateTime(String dateTime) {
        LocalDateTime dt1 = LocalDateTime.parse(dateTime, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return dt1;
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
        } catch (MissingTimeException e) {
            System.out.println("  You do realise deadlines and events usually have a time or date, right?\n"
                    + e.getMessage());
        } catch (UnknownCommandException e) {
            System.out.println("  If you want my help, the least you could do is say something I understand.");
        }
        System.out.println(BORDER);
    }

    public static void main(String[] args) {

        String mort = "                               .---.        .-----------\n"
                + "                              /     \\  __  /    ------\n"
                + "                             / /     \\(  )/    -----\n"
                + "                            //////   ' \\/ `   ---\n"
                + "                           //// / // :    : ---\n"
                + "                          // /   /  /`    '--\n"
                + "                         //          //..\\\\\n"
                + ">>==================================UU[**]UU==================================<<\n"
                + "                                    '//||\\\\`\n"
                + "                                      ''``";

        System.out.println(mort);
        System.out.println("  Oh, it's you again...\n  Mort, begrudgingly at your service.");
        Scanner sc = new Scanner(System.in);
        System.out.println("  Hmph, what do you want now?\n" + BORDER);
        String date = "2019-02-02";
        System.out.println(parseDateTime(date));

        while (isRunning) {
            System.out.println();
            String input = sc.nextLine();
            System.out.println();
            printResponse(input, sc);
        }
    }
}
