package command;

import clevernotbot.History;
import clevernotbot.Storage;
import clevernotbot.UI;
import exception.CleverNotBotException;
import task.Task;
import task.TaskList;

/**
 * Represents a command to mark a task.
 */
public class UndoCommand extends Command {

    /**
     * Constructor for the UndoCommand.
     *
     * @param commandName Description of command.
     * @param exit        Checking if program intends to exit.
     */
    public UndoCommand(String commandName, boolean exit) {
        super(commandName, exit);
    }

    /**
     * Runs the undo command to undo the previous action.
     *
     * @param tasks   The task list used to store all tasks.
     * @param textBox UI for the textbox.
     * @param storage The data where it is stored.
     * @param history All the history done for adding and removing stuff.
     * @return A String type response.
     * @throws CleverNotBotException Gives an exception.
     */
    @Override
    public String run(TaskList tasks, UI textBox, Storage storage, History history) throws CleverNotBotException {
        if(history.canUndo()) {
            TaskList prevState = history.retrieveLastHistory(tasks);
            storage.writeToFile(prevState.getTaskList());
            return "The previous command has been undone!";
        } else {
            throw new CleverNotBotException("There is no more command to undo!",textBox);
        }
    }
}
