package duke.command;

import duke.CommandHistory;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * The DeleteCommand class represents the delete command given by user.
 */
public class DeleteCommand extends Command {
    private final int position;
    private Task taskRemoved = null;

    /**
     * The constructor of the DeleteCommand class.
     * Sets the position of the task to be deleted
     * to the local variable.
     *
     * @param position Position of the task in taskList to be removed.
     */
    public DeleteCommand(int position) {
        this.position = position;
    }

    /**
     * Removes the task from the task list.
     * Returns the message that the task was removed.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that task was removed.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList,
            CommandHistory commandHistory) {
        taskRemoved = taskList.getTask(position - 1);
        commandHistory.addCommand(this);
        taskList.remove(position - 1, storage);
        String commandMessage = "Noted! This task has been successfully removed!";
        return ui.displayCommandMessage(commandMessage, taskRemoved, taskList.getSize());
    }

    /**
     * Adds the task that has been deleted back into the task list.
     * Returns the message that task has been added back.
     *
     * @param ui Ui object which handles the interaction with the user.
     * @param storage Storage object which handles interaction with data in file.
     * @param taskList List of tasks.
     * @param commandHistory History of commands made.
     * @return The message that the task has been added back.
     */
    @Override
    public String undoExecute(Ui ui, Storage storage, TaskList taskList, CommandHistory commandHistory) {
        if (taskRemoved == null) {
            return ui.displayCommandMessage("No task has been deleted previously",
                    null, null);
        }
        if (taskRemoved instanceof Event) {
            Event event = (Event) taskRemoved;
            taskList.add(event, storage);
            String message = "This event has been successfully re-added";
            return ui.displayCommandMessage(message, event, taskList.getSize() - 1);
        }
        if (taskRemoved instanceof ToDo) {
            ToDo toDo = (ToDo) taskRemoved;
            taskList.add(toDo, storage);
            String message = "This ToDo has been successfully re-added";
            return ui.displayCommandMessage(message, toDo, taskList.getSize() - 1);
        }
        if (taskRemoved instanceof Deadline) {
            Deadline deadline = (Deadline) taskRemoved;
            taskList.add(deadline, storage);
            String message = "This deadline has been successfully re-added";
            return ui.displayCommandMessage(message, deadline, taskList.getSize() - 1);
        }

        return null;
    }
}
