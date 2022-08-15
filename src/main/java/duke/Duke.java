package duke;

import duke.tasks.TaskManager;
import utils.Constants;
import utils.DukeUtils;

import java.util.Scanner;

public class Duke {

    private final TaskManager tm;

    Duke() {
        tm = new TaskManager();
    }

    public void startDuke() {
        sendGreetings();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            String[] inputs = sc.nextLine().trim().split(" ", 2);
            String inputCommand = inputs[0];
            String inputDesc = (inputs.length == 1) ? "" : inputs[1];
            Command command = Command.contains(inputCommand);

            try {
                switch (command.isValidInput(inputDesc)) {
                case LIST:
                    tm.printTasks();
                    break;
                case BYE:
                    sendExit();
                    sc.close();
                    isRunning = false;
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
                DukeUtils.printMessage(e.getMessage());
            }
        }
    }

    private void sendGreetings() {
        DukeUtils.printMessage(Constants.MSG_GREETINGS);
    }

    private void sendExit() {
        DukeUtils.printMessage(Constants.MSG_EXIT);
    }

}