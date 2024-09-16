package duke.commands;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Command for adding ToDo Task.
 */
public class ToDoCommand extends TaskCommand {
    private String description;

    /**
     * ToDoCommand constructor.
     *
     * @param remainingCommand ToDo description.
     */
    public ToDoCommand(String remainingCommand) {
        description = remainingCommand;
    }

    /**
     * Executes a ToDo command.
     *
     * @param tasks TaskList that stores Tasks objects.
     * @param ui Ui that handles user interaction.
     * @param storage Storage that handles storing information on memory files.
     * @throws DukeException if task description exists.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! Please enter a valid todo format (todo <description>)");
        }
        if (taskDescriptionExists(tasks, description)) {
            throw new DukeException("OOPS!!! The task description already exists. Please use a different "
                    + "task description.");
        }
        try {
            Task task = new ToDo(description);
            tasks.addTask(task, description);
            storage.addTaskToDisk(task.taskMemo() + System.lineSeparator());

            return ui.printAddTask(task, tasks.getTaskListSize());
        } catch (IOException e) {
            return (e.getMessage());
        }
    }

    /**
     * Checks if program should exit.
     *
     * @return false as program should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
