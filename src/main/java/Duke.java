import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    protected static final String TOP_WINDOW = "╔══════════════════════════════════════════════╗";
    protected static final String BOTTOM_WINDOW = "╚══════════════════════════════════════════════╝";
    private static final String GREETING = "Hi, I'm Ploopy! Nice to meet you!\n\tWhats up?";
    private static final String FAREWELL = "Okay then, see ya later :)";
    private static final String COMPLETED_TASK = "Nice! You've completed this task. I'll mark it as done.";
    private static final String incompleteTask = "Alright this task has been marked as undone.";
    private static final String addedTask = "I've added this task to your list.\n\tHere you go: ";

    //Exceptions:
    private static final String NoInputMessage = "You didn't say what I should do! (ಠ_ʖಠ)";
    private static final String EmptyCommandMessage = "What should I do with the ";
    private static final String NonsenseInputMessage = "I can't do that right now, sorry ┐(‘～`；)┌";

    public static final String textArt = "\n" +
            "\t███████████████████████████████████\n" +
            "\t█▄─▄▄─█▄─▄███─▄▄─█─▄▄─█▄─▄▄─█▄─█─▄█\n" +
            "\t██─▄▄▄██─██▀█─██─█─██─██─▄▄▄██▄─▄██\n" +
            "\t▀▄▄▄▀▀▀▄▄▄▄▄▀▄▄▄▄▀▄▄▄▄▀▄▄▄▀▀▀▀▄▄▄▀▀";

    private static ArrayList<Task> tasks;

    public static void start() {
        System.out.println(messageFormatter(textArt));
        System.out.println(messageFormatter(GREETING));
        tasks = new ArrayList<>();
        command();
    }

    protected static String messageFormatter(String input) {
        return TOP_WINDOW + "\n\t" + input + "\n" + BOTTOM_WINDOW;
    }

    private static void displayList() {
        System.out.println(TOP_WINDOW);
        int index = 1;
        for (Task item : tasks) {
            System.out.println("\t" + index + "." + item);
            index++;
        }
        System.out.println(BOTTOM_WINDOW);
    }

    private static void markTask(int taskIndex) throws DukeException {
        if (taskIndex > 0 && taskIndex <= tasks.size()) {
            Task current = tasks.get(taskIndex - 1);
            current.markDone();
            System.out.println(messageFormatter(COMPLETED_TASK + "\n\t" + " " + current));
        } else throw new DukeException(messageFormatter("That task number doesn't exist on your list!"));
    }

    private static void unmarkTask(int taskIndex) throws DukeException {
        if (taskIndex > 0 && taskIndex <= tasks.size()) {
            Task current = tasks.get(taskIndex - 1);
            current.unmark();
            System.out.println(messageFormatter(incompleteTask + "\n\t" + " " + current));
        } else throw new DukeException(messageFormatter("That task number doesn't exist on your list!"));
    }

    private static void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            String message = "Deleted: " + tasks.get(taskNumber - 1) + "\n\tYou have "
                    + tasks.size() + " task(s) remaining.";
            tasks.remove(taskNumber - 1);
            System.out.println(messageFormatter(message));
        } else throw new DukeException(messageFormatter("That task number doesn't exist on your list!"));
    }

    private static void addTaskMessage(Task task) {
        tasks.size();
        String message = addedTask + task.toString() + "\n\tYou have " + tasks.size() + " tasks in your list.";
        System.out.println(messageFormatter(message));
    }

    private static boolean isEmptyCommand(String command, int size) {
        // this method removes the whitespace of a command and checks if its length = size
        // true means empty command
        return command.trim().length() == size;
    }

    private static void createToDo(String input) throws DukeException {
        if (!isEmptyCommand(input, "todo".length())) {
            Task newTask = new ToDo(input.split(" ")[1]);
            tasks.add(newTask);
            addTaskMessage(newTask);
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " todo"));
    }

    private static void createDeadline(String input) throws DukeException {
        if (!isEmptyCommand(input, "deadline".length())) {
            String date = input.split("/by ")[1];
            String name = input.split(" ")[1];
            Task newTask = new Deadline(name, date);
            tasks.add(newTask);
            addTaskMessage(newTask);
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " deadline"));
    }

    private static void createEvent(String input) throws DukeException {
        if (!isEmptyCommand(input, "event".length())) {
            String date = input.split("/by ")[1];
            String name = input.split(" ")[1];
            Task newTask = new Event(name, date);
            tasks.add(newTask);
            addTaskMessage(newTask);
        } else throw new DukeException(messageFormatter(EmptyCommandMessage + " event"));
    }

    private static void command() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String command;
        while (!input.equals("bye")) {
            try {
                if (input.isBlank() || input.isEmpty()) throw new DukeException(messageFormatter(NoInputMessage));
                command = input.split(" ")[0];
                switch (command) {
                    case "mark":
                        markTask(input.charAt(5) - 48);
                        break;
                    case "unmark":
                        unmarkTask(input.charAt(7) - 48);
                        break;
                    case "list":
                        displayList();
                        break;
                    case "delete":
                        deleteTask(input.charAt(7) - 48);
                        break;
                    case "todo":
                        createToDo(input);
                        break;
                    case "deadline":
                        createDeadline(input);
                        break;
                    case "event":
                        createEvent(input);
                        break;
                    default:
                        throw new DukeException(messageFormatter(NonsenseInputMessage));
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            input = scanner.nextLine();
        }
        System.out.println(messageFormatter(FAREWELL));
    }

    public static void main(String[] args) {
        start();
//        String test = "deadline csdfcds /by 02/10/2000 1822";
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm");
//        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy kkmm");
//        //LocalDateTime dateTime = LocalDateTime.parse(test.split("/by")[1], inputFormatter);
//        System.out.println(test.split("/by ")[1]);
    }
}
