import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.function.Function;

/**
 * Simple CLI chatbot that reacts on user input.
 */
public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String PADDING = "  ";
    private static final Scanner scanner = new Scanner(System.in);
    private static Log log = new Log();
    private static final int INVALID_INDEX = -1;
    private static final String STORAGE_PATH = "data/duke.txt";
    private static final Storage STORAGE = new Storage(STORAGE_PATH);

    /**
     * Main function for the chatbot.
     * 
     * @param args System arguments. Not used for this program.
     */
    public static void main(String[] args) {
        displayLogo();
        displayGreetingMessage();
        load();
        listenForInputs();
    }

    private static void load() {
        try {
            log = new Log(STORAGE.readFile());
            formatAndPrint("Successfully loaded from storage file.");
        } catch (

        DukeException e) {
            formatAndPrint(e.getMessage());
        }
    }

    private static void displayLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    private static void displayGreetingMessage() {
        String[] messages = { "Hello! I'm Duke.", "What can I do for you?" };
        formatAndPrint(List.<String>of(messages));
    }

    private static void formatAndPrint(List<? extends String> texts) {
        String divider = "    ____________________________________________________________";
        String padding = "     ";
        System.out.println(divider);
        texts.forEach((text) -> System.out.println(padding + text));
        System.out.println(divider);
    }

    private static void formatAndPrint(String text) {
        formatAndPrint(List.of(text));
    }

    private static Command inputToCommand(String input) {
        Command command;
        try {
            command = Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            command = Command.UNKNOWN;
        }
        return command;
    }

    private static void listenForInputs() {
        if (!scanner.hasNext()) {
            return;
        }
        String input = scanner.next();
        Command command = inputToCommand(input);
        switch (command) {
            case BYE:
                displayExitMessage();
                return;
            case LIST:
                listTasks();
                break;
            case TODO:
                addTodo();
                break;
            case DEADLINE:
                addDeadline();
                break;
            case EVENT:
                addEvent();
                break;
            case MARK:
                markTask();
                break;
            case UNMARK:
                unmarkTask();
                break;
            case DELETE:
                deleteTask();
                break;
            case UNKNOWN:
                displayUnknownCommandMessage(input);
        }
        listenForInputs();
    }

    private static void displayExitMessage() {
        formatAndPrint("Bye bye");
    }

    private static void displayUnknownCommandMessage(String input) {
        formatAndPrint("Unknown command: " + input);
    };

    private static void listTasks() {
        formatAndPrint(log.getLogs());
    }

    private static void addTodo() {
        addTask((input) -> CommandParser.parseTodo(input));
    }

    private static void addDeadline() {
        addTask((input) -> CommandParser.parseDeadline(input));
    }

    private static void addEvent() {
        addTask((input) -> CommandParser.parseEvent(input));
    }

    private static void addTask(DukeParser<Task> taskParser) {
        try {
            String input = parseLine(scanner);
            Task task = taskParser.parse(input);
            updateLog((log) -> log.add(task),
                    "Task added: ");
            STORAGE.save(task);
        } catch (DukeException e) {
            formatAndPrint(e.getMessage());
        }
    }

    private static void deleteTask() {
        try {
            int taskIndex = parseIndex();
            updateLog(log -> log.delete(taskIndex),
                    "Task deleted: ");
        } catch (DukeException e) {
            formatAndPrint(e.getMessage());
        }
    }

    private static void updateLog(Function<Log, Task> logUpdater, String updateMessage) {
        Task task = logUpdater.apply(log);
        displayUpdateMessage(task, updateMessage, log.size());
    }

    private static void displayUpdateMessage(Task task, String updateMessage, int taskListSize) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add(updateMessage);
        toPrint.add(PADDING + task.toString());
        toPrint.add("There are now " + taskListSize + " tasks in the list.");
        formatAndPrint(toPrint);
    }

    private static void markTask() {
        markUnmarkWrapper((taskIndex) -> {
            List<String> toPrint = new ArrayList<>();
            log.markTask(taskIndex);
            toPrint.add("I have marked this task as done: ");
            toPrint.add(PADDING + log.markTask(taskIndex).toString());
            return toPrint;
        });
    }

    private static void unmarkTask() {
        markUnmarkWrapper((taskIndex) -> {
            List<String> toPrint = new ArrayList<>();
            toPrint.add("I have unmarked the completion of this task: ");
            toPrint.add(PADDING + log.unmarkTask(taskIndex).toString());
            return toPrint;
        });
    }

    private static void markUnmarkWrapper(Function<Integer, List<String>> markingFunction) {
        int taskIndex;
        try {
            taskIndex = parseIndex();
        } catch (DukeException e) {
            formatAndPrint(e.getMessage());
            return;
        }
        if (taskIndex == INVALID_INDEX) {
            return;
        }
        List<String> toPrint = markingFunction.apply(taskIndex);
        formatAndPrint(toPrint);
    }

    /**
     * 
     * @return Parse index given by user in 0-index notation. -1 if index not in
     *         range, not an integer or not found.
     * @throws DukeException Exceptions from the user inputs to Duke.
     */
    private static int parseIndex() throws DukeException {
        int inputIndex;
        try {
            inputIndex = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new DukeException("The index must be an Integer!", e);
        } catch (NoSuchElementException e) {
            throw new DukeException("No index detected!", e);
        }
        int actualIndex = inputIndex - 1;
        if (!isIndexInRange(actualIndex)) {
            throw new DukeException("Index out of range!");
        }
        return actualIndex;
    }

    private static String parseLine(Scanner sc) throws DukeException {
        String input;
        try {
            sc.skip(sc.delimiter());
            input = sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeException("The description cannot be empty!", e);
        }
        if (input.trim().length() == 0) {
            throw new DukeException("The description cannot be empty!");
        }
        return input;
    }

    private static boolean isIndexInRange(int index) {
        return index >= 0 && index < log.size();
    }
}
