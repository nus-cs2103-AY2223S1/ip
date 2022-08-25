package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.processor.TaskList;
import duke.task.Task;

/**
 * Class that represents the command 'find'.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructs an instance of FindCommand.
     *
     * @param keyword The keyword associated with the task.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Finds the tasks with the associated keyword.
     *
     * @param tasks List of tasks that has been inputted by the user.
     * @throws DukeException An exception is thrown if the bot cannot find any tasks with the keyword.
     */
    @Override
    public void execute(TaskList tasks) throws DukeException {
        try {
            TaskList tasksWithKeyword = new TaskList();
            Ui ui = new Ui();
            for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
                Task task = tasks.getTask(i);
                String taskName = task.getTaskName();
                if (taskName.contains(keyword)) {
                    tasksWithKeyword.addTask(task);
                }
            }
            ui.find(tasksWithKeyword);
        } catch (Exception e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I can't find any tasks with that keyword :-(");
        }
    }
}
