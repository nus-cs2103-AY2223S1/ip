package commands;

import duke.Storage;
import duke.Ui;
import tasks.TaskList;

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
    public String execute(TaskList taskList, Ui ui, Storage s) {
        s.end(taskList);
        return ui.printByeStatement();
    }
}
