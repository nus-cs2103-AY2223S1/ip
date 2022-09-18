package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Assigns tags to tasks.
 */
public class TagCommand extends Command {
    private String tag;
    private final int taskNum;

    /**
     * Finds tasks based on keyword when command is called.
     *
     * @param taskNum index of task to tag.
     * @param tag tag content.
     */
    public TagCommand(int taskNum, String tag) {
        this.tag = tag;
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (taskNum < 0 || taskNum >= tasks.size()) {
            throw new DukeException("◔ ‸◔？ The index of the task is not in the list.");
        }

        System.out.println(taskNum);
        System.out.println(tag);

        tasks.tagTask(taskNum, tag);
        ui.outputMessage("≖‿≖✧ Successfully tagged task No." + (taskNum + 1) + " with # " + tag);
    }
}
