package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.time.format.DateTimeParseException;

/**
 * The class for a Duke, which also contains the main method which the program is run from.
 *
 * @author kaij77
 * @version 0.1
 */
public class Duke {
    private TaskList tasks;
    private Ui ui;

    /**
     * Public constructor for a Duke.
     */
    public Duke() {
        ui = new Ui();
        tasks = new TaskList();
    }

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String GREETING = "Hello! I'm duke.Duke\n"
            + "What can I do for you?\n";

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Runs Duke by scanning and reading the input from the user and executing the Command.
     */
    public void run() {
        System.out.println(LOGO);
        System.out.println(GREETING);
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.read();
                Command command = Parser.parseInput(userInput);
                command.execute(this.tasks, this.ui);
                isExit = command.getExit();
            } catch (DukeException | IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            } catch (DateTimeParseException e) {
                System.out.println("Please format date in YYYY-MM-DD");
            }
        }
    }
}
