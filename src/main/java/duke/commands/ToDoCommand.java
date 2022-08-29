package duke.commands;

import duke.*;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.IOException;

/**
 * Command for adding ToDo Task.
 */
public class ToDoCommand extends Command {
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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (description.equals("")) {
            throw new DukeException("OOPS!!! Please enter a valid todo format (todo <description>)");
        }
        try {
            Task task = new ToDo(description);
            tasks.addTask(task);
            storage.addTaskToDisk(task.taskMemo() + System.lineSeparator());
            ui.printAddTask(task, tasks.getTaskListSize());
        } catch (IOException e) {
            System.out.println("     " + e.getMessage());
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
