package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.exception.DukeException;
import duke.task.TasksList;
import java.util.Scanner;

public class Duke {
    private TasksList tasksList;
    private boolean hasEnded = false;
    private Ui ui;
    private static String TASKS_STORAGE_PATH = "./data/duke.txt";

    public Duke() {
        this.tasksList = new TasksList(Duke.TASKS_STORAGE_PATH);
        this.ui = new Ui();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public void run() {
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

    public String getInput(Scanner sc) {
        System.out.println();
        return sc.nextLine();
    }
}

