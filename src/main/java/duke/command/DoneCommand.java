package duke.command;

import duke.exception.DukeException;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * Marks a task as done when command is called.
 */
public class DoneCommand extends Command {
    private final int taskNum;

    /**
     * Marks task as done based on index entered when command is called.
     *
     * @param taskNum index of task to be marked as done.
     */
    public DoneCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskNum < 1 || taskNum > tasks.size()) {
            throw new DukeException("◔ ‸◔？ The index of the task is not in the list :(");
        }

        tasks.markTaskAsDone(taskNum - 1);
        ui.outputMessage("Good job! ʕ•̀ω•́ʔ I've marked this task as done:\n"
                + tasks.get(taskNum - 1).toString());
        storage.save(tasks);
    }
}
