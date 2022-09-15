package Duke.Commands;

import Duke.Tasks.TaskList;

/**
 * Class that denotes the command of sorting all tasks based on the emergency.
 */
public class SortAllCommand extends UserCommand {

    /**
     * Public constructor of SortAllCommand class.
     * @param tasks TaskList containing current tasks.
     */
    public SortAllCommand(TaskList tasks){
        super(tasks);
    }
    @Override
    public String execute() {
        String output = "These are all tasks after sorting:\n";
        output += tasks.sortAll();
        return output;
    }
}
