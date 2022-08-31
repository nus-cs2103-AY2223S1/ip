package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.task.TasksList;


/**
 * Represents the chat-bot.
 */
public class Duke {
    private static final String TASKS_STORAGE_PATH = "./data/duke.txt";
    private TasksList tasksList;
    private boolean hasEnded = false;
    private Ui ui;


    /**
     * Creates a new Duke instance.
     */
    public Duke() {
        this.tasksList = new TasksList(Duke.TASKS_STORAGE_PATH);
        this.ui = new Ui();
    }

    /**
     * Starts the Duke bot.
     * @param args The arguments taken in.
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        this.ui.greet();
        while (!hasEnded) {
            try {
                String userInput = getInput(sc);
                Command command = new Parser().handleCommand(userInput, this.tasksList);
                String message = command.execute();
                this.ui.printMessage(message);
                if (command instanceof ExitCommand) {
                    sc.close();
                    hasEnded = true;
                    break;
                }
                tasksList.saveTasks();
            } catch (DukeException exception) {
                this.ui.printExceptionMessage(exception);
            }
        }
    }

    private String getInput(Scanner sc) {
        System.out.println();
        return sc.nextLine();
    }
}

