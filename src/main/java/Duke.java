import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * The main method of the chatbot, as well as its startup and teardown.
 */
public class Duke {
    /** List of commands */
    private static ArrayList<CommandMatcher> commands;

    private static Optional<Task> getTask(String index) {
        try {
            int idx = Integer.parseInt(index);
            Task task = TaskList.getTaskList().get(idx - 1);
            return Optional.of(task);
        } catch (NumberFormatException ex) {
            Ui.messagePrint("Sorry, I didn't understand " + index + ", please give me a number.");
            return Optional.empty();
        } catch (IndexOutOfBoundsException ex) {
            Ui.messagePrint("Sorry, the number " + index + ", wasn't in the range.");
            return Optional.empty();
        }
    }

    private static void handleCommand(String command) {
        for (CommandMatcher matcher : commands) {
            if (matcher.run(command)) {
                break;
            }
        }
    }

    /**
     * Runs the chatbot execution.
     * @param args Command line args which are not used.
     */
    public static void main(String[] args) {
        Ui.greet();
        TaskList.initializeTaskList();
        commands = Parser.getCommands();
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;
        while (keepRunning) {
            String command = input.nextLine();
            if (command.equals("bye")) {
                keepRunning = false;
            } else {
                handleCommand(command);
            }
        }
        TaskList.finalizeTaskList();
        Ui.leave();
    }
}
