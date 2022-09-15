package duke.commands;

import duke.tasks.TaskList;

/**
 * Class that denotes the command of saving current data.
 */
public class SaveCommand extends UserCommand{
    /**
     * Public constructor of UserCommand class.
     *
     * @param tasks TaskList containing current tasks.
     */
    public SaveCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public String execute(){
        return "All data are saved automatically.";
    }
}
