package duke.command;

import duke.exception.DukeException;
import duke.exception.MatchException;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.UI;

import java.util.ArrayList;

/**
 * Created when user inputs "find".
 */
public class FindCommand extends Command {
    private String find;

    /**
     * Constructor for a find command.
     * @param find All tasks in the task list that match the given string.
     */
    public FindCommand(String find) {
        this.find = find;
    }

    /**
     * Finds all matching tasks in the task list.
     *
     * @param storage Storage of the current Duke program.
     * @param ui UI of the current Duke program.
     * @param taskList Tasklist of the current Duke program.
     * @throws DukeException If a match is not found in the task list.
     */
    @Override
    public void execute(Storage storage, UI ui, TaskList taskList) throws DukeException {
        TaskList tasks = new TaskList(new ArrayList<>());
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.getTask(i).getDescription().contains(find)) {
                tasks.add(taskList.getTask(i));
            }
        }
        if (tasks.size() == 0) {
            throw new MatchException();
        } else {
            ui.showMatchingTasks();
            taskList.read();
        }
    }
}
