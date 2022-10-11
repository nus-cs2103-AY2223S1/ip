package isara.command;

import java.io.File;

import isara.Ui;
import isara.exception.IsaraException;
import isara.processor.TaskList;
import isara.storage.Storage;
import isara.task.Task;


/**
 * Class that represents the command 'find'.
 */
public class FindCommand extends Command {
    /**
     * Keyword to help find the tasks.
     */
    private String keyword;

    /**
     * Constructs an instance of FindCommand.
     *
     * @param keyword The keyword associated with the task.
     */
    public FindCommand(String keyword) {
        super(false);
        this.keyword = keyword;
    }

    /**
     * Finds the tasks with the associated keyword.
     *
     * @param tasks The list of tasks where the command is executed.
     * @param file The file to write into.
     * @param storage The storage where the file is located.
     * @return String that is related to the command line.
     * @throws IsaraException An exception is thrown if the bot cannot find any tasks with the keyword.
     */
    @Override
    public String execute(TaskList tasks, File file, Storage storage) throws IsaraException {
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
            return ui.find(tasksWithKeyword);
        } catch (Exception e) {
            throw new IsaraException("☹ OOPS!!! I'm sorry, but I can't find any tasks with that keyword :-(");
        }
    }
}
