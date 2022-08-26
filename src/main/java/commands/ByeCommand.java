package commands;

import duke.Ui;
import tasks.*;

/**
 * ByeCommand causes the program to end.
 */
public class ByeCommand extends Command {
    boolean toExitProgram = true;

    /**
     * Constructor for ByeCommand
     */
    public ByeCommand() {
        super();
    }

    @Override
    /**
     * Prints exit statement for program.
     *
     * @param taskList List of tasks to save before exiting.
     */
    public void run(TaskList taskList) {
        Ui.byeStatement();
    }
}
