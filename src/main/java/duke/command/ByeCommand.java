package duke.command;

import duke.task.TaskList;
import duke.Ui;

/**
 * Class which inherits the Command class for a ByeCommand
 *
 * @author kaij77
 * @version 0.1
 */

public class ByeCommand extends Command {
    /**
     * Closes Duke after the user says bye.
     *
     * @param taskList A list of tasks
     * @param ui A ui which prints output to the user
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        System.out.println("Bye. Hope to see you again soon!");
        setExit();
    }
}
