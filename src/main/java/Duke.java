import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Supplier;

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
    private static final Log log = new Log();
    private static final int INVALID_INDEX = -1;
    private static final String DEADLINE_INDICATOR_PATTERN = "\\s*/by\\s*";
    private static final String EVENT_INDICATOR_PATTERN = "\\s*/at\\s*";

    /**
     * Main function for the chatbot.
     * 
     * @param args System arguments. Not used for this program.
     */
    public static void main(String[] args) {
        displayLogo();
        displayGreetingMessage();
        listenForInputs();
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

    private static void listenForInputs() {
        if (!scanner.hasNext()) {
            return;
        }
        String input = scanner.next();
        switch (input) {
            case "bye":
                displayExitMessage();
                return;
            case "list":
                listTasks();
                break;
            case "todo":
                addTodo();
                break;
            case "deadline":
                addDeadline();
                break;
            case "event":
                addEvent();
                break;
            case "mark":
                markTask();
                break;
            case "unmark":
                unmarkTask();
                break;
            default:
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
        addTask(() -> parseTodo());
    }

    private static void addDeadline() {
        addTask(() -> parseDeadline());
    }

    private static void addEvent() {
        addTask(() -> parseEvent());
    }

    private static void addTask(TaskParser taskParser) {
        try {
            Task task = taskParser.get();
            displayTaskAddMessage(task);
        } catch (DukeException e) {
            formatAndPrint(e.getMessage());
        }
    }

    private static void displayTaskAddMessage(Task task) {
        log.add(task);
        List<String> toPrint = new ArrayList<>();
        toPrint.add("Task added: ");
        toPrint.add(PADDING + task.toString());
        toPrint.add("There are now " + log.size() + " tasks in the list.");
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
            throw new DukeException("Index out of range");
        }
        return actualIndex;
    }

    private static String parseLine(Scanner sc) throws DukeException {
        String input;
        try {
            input = sc.nextLine();
        } catch (NoSuchElementException e) {
            throw new DukeException("The description cannot be empty!", e);
        }
        if (input.trim().length() == 0) {
            throw new DukeException("The description cannot be empty!");
        }
        return input;
    }

    private static Todo parseTodo() throws DukeException {
        String input = parseLine(scanner);
        return new Todo(input);
    }

    private static Deadline parseDeadline() throws DukeException {
        String input = parseLine(scanner);
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(DEADLINE_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String by = lineScanner.next();
            return new Deadline(description, by);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format for adding deadline!", e);
        }
    }

    private static Event parseEvent() throws DukeException {
        String input = parseLine(scanner);
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(EVENT_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String at = lineScanner.next();
            return new Event(description, at);
        } catch (NoSuchElementException e) {
            throw new DukeException("Invalid format for adding event!", e);
        }
    }

    private static boolean isIndexInRange(int index) {
        return index >= 0 && index < log.size();
    }
}
