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

    /**
     * Prints exit statement for program.
     *
     * @param taskList List of tasks to save before exiting.
     * @param ui Ui to retrieve return string from.
     * @param s Storage containing the list of tasks or where tasks should be saved.
     * @return Bye string to be displayed by program.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage s) {
        s.end(taskList);
        return ui.printByeStatement();
    }
}
