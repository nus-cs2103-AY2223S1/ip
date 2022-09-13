package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import exceptions.InvalidCommandException;

/**
 * Deletes a task by index
 */
public class DeleteTaskCommand extends Command implements ContinuableCommand {

    /**
     * Creates a new DeleteTaskCommand
     * @param duke the instance of duke that spawned this command
     */
    public DeleteTaskCommand(Duke duke) {
        super(duke);
    }

    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        if (!elements.hasNext()) {
            duke.sendMessage("You have to specify what you want me to delete.");
        }

        String thisElement = elements.next();

        if (thisElement.equals("all")) {
            askToDeleteAllTasks();
        } else {
            int index;

            try {
                index = Integer.parseInt(thisElement) - 1;
                if (index < 0 || index >= duke.getTaskList().getSize()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                throw new InvalidCommandException("This index is out of range");
            }

            deleteAt(index);
            duke.sendMessage("Okay, I've removed this task,");
        }
    }

    @Override
    public void continueParse(Iterator<String> elements) throws InvalidCommandException {
        if (!elements.hasNext()) {
            throw new InvalidCommandException("Nothing was given.");
        }

        String thisElement = elements.next();

        switch (thisElement.toLowerCase()) {
        case "y":
        case "yes":
            deleteAllTasks();
            duke.sendMessage("Okay, all your tasks are cleared.");
            break;

        case "n":
        case "no":
            duke.sendMessage("I'll leave the task list as is");
            break;

        default:
            throw new InvalidCommandException("The previous command was ignored.");
        }
    }

    private void deleteAt(int index) {
        assert index >= 0 && index < duke.getTaskList().getSize();
        duke.getTaskList().remove(index);
    }

    private void askToDeleteAllTasks() {
        duke.attachState(this);
        duke.sendMessage("Are you sure you want to delete all tasks?");
    }

    private void deleteAllTasks() {
        duke.getTaskList().clear();
    }
}
