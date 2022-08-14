import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
        Task newTodo;
        try {
            newTodo = parseTodo();
        } catch (NoSuchElementException e) {
            formatAndPrint("The description cannot be empty!");
            return;
        }
        addTaskToLog(newTodo);
    }

    private static void addDeadline() {
        Task newDeadline;
        try {
            newDeadline = parseDeadline();
        } catch (NoSuchElementException e) {
            formatAndPrint("Invalid format for adding deadline!");
            return;
        }
        addTaskToLog(newDeadline);
    }

    private static void addEvent() {
        Task newEvent;
        try {
            newEvent = parseEvent();
        } catch (NoSuchElementException e) {
            formatAndPrint("Invalid format for adding event!");
            return;
        }
        addTaskToLog(newEvent);
    }

    private static void addTaskToLog(Task task) {
        log.add(task);
        displayTaskAddMessage(task, log.size());
    }

    private static void displayTaskAddMessage(Task task, Integer taskListSize) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add("Task added: ");
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
        int taskIndex = parseIndex();
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
     */
    private static int parseIndex() {
        int inputIndex;
        try {
            inputIndex = scanner.nextInt();
        } catch (InputMismatchException e) {
            formatAndPrint("The index must be an Integer!");
            return INVALID_INDEX;

        } catch (NoSuchElementException e) {
            formatAndPrint("No index detected!");
            return INVALID_INDEX;
        }
        int actualIndex = inputIndex - 1;
        if (!isIndexInRange(actualIndex)) {
            formatAndPrint("Index out of range!");
            return INVALID_INDEX;
        }
        return actualIndex;
    }

    private static Todo parseTodo() throws NoSuchElementException {
        String input = scanner.nextLine();
        return new Todo(input);
    }

    private static Deadline parseDeadline() throws NoSuchElementException {
        String input = scanner.nextLine();
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(DEADLINE_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String by = lineScanner.next();
            return new Deadline(description, by);
        }
    }

    private static Event parseEvent() throws NoSuchElementException {
        String input = scanner.nextLine();
        try (Scanner lineScanner = new Scanner(input)
                .useDelimiter(EVENT_INDICATOR_PATTERN)) {
            String description = lineScanner.next();
            String at = lineScanner.next();
            return new Event(description, at);
        }
    }

    private static boolean isIndexInRange(int index) {
        return index >= 0 && index < log.size();
    }
}
