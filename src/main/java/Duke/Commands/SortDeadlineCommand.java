package duke.commands;

import duke.tasks.TaskList;

/**
 * Class that denotes the command of sorting all deadlines based on emergency.
 */
public class SortDeadlineCommand extends UserCommand {

    /**
     * Public constructor of SortDeadlineCommand class.
     * @param tasks TaskList containing current tasks.
     */
    public SortDeadlineCommand(TaskList tasks){
        super(tasks);
    }

    @Override
    public String execute() {
        String output = "These are all deadlines after sorting based on emergency:\n";
        output += tasks.sortDeadline();
        return output;
    }
}
