package duke;

import duke.tasks.TaskManager;
import utils.Constants;
import utils.DukeUtils;

import java.util.Scanner;

public class Duke {

    private final TaskManager tm;
    private boolean isDukeRunning;

    Duke() {
        tm = new TaskManager();
        isDukeRunning = true;
    }

    public void startDuke() {
        DukeUtils.printMessages(Constants.MSG_GREETINGS);
        Scanner scanner = new Scanner(System.in);

        while (isDukeRunning) {
            processCommand(scanner);
        }

        scanner.close();
        DukeUtils.printMessages(Constants.MSG_EXIT);
    }

    private void processCommand(Scanner scanner) {
        String[] inputs = scanner.nextLine().trim().split(" ", 2);
        String inputCommand = inputs[0].trim();
        String inputDesc = (inputs.length == 1) ? "" : inputs[1].trim();

        try {
            Command command = Command.contains(inputCommand).hasValidInput(inputDesc);
            switch (command) {
            case LIST:
                tm.printTasks();
                break;
            case BYE:
                isDukeRunning = false;
                break;
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT:
                tm.addTask(inputCommand, inputDesc);
                break;
            case MARK:
                tm.markTask(inputDesc);
                break;
            case UNMARK:
                tm.unmarkTask(inputDesc);
                break;
            case DELETE:
                tm.deleteTask(inputDesc);
                break;
            case INVALID:
                throw new DukeException(Constants.ERROR_UNKNOWN_COMMAND);
            }
        } catch (DukeException e) {
            DukeUtils.printMessages(e.getMessage());
        }
    }
}