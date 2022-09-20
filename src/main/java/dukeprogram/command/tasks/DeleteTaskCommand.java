package dukeprogram.command.tasks;

import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Collectors;

import dukeprogram.Duke;
import dukeprogram.command.Command;
import dukeprogram.command.ContinuableCommand;
import dukeprogram.tasks.Task;
import dukeprogram.userinterface.Widget;
import exceptions.InvalidCommandException;
import utilities.StringUtilities;

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

        String thisElement = StringUtilities.concatByDelimiter(elements, " ");

        if (thisElement.equals("all")) {
            askToDeleteAllTasks();
            return;
        }

        int index = parseTaskIndex(thisElement);

        Task task = deleteAt(index);
        duke.sendMessage("Okay, I've removed this task,",
                new Widget(task.createLabelWidget()));

        duke.save();
    }

    private int parseTaskIndex(String thisElement) throws InvalidCommandException {
        try {
            int index = Integer.parseInt(thisElement) - 1;
            if (index < 0 || index >= duke.getTaskList().getSize()) {
                throw new IndexOutOfBoundsException();
            }

            return index;
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("This index is out of range");
        } catch (NumberFormatException e) {
            Task[] tasksFound = duke.getTaskList().findTasks(thisElement);

            if (tasksFound.length == 0) {
                throw new InvalidCommandException("I couldn't find any tasks with " + thisElement);
            }

            if (tasksFound.length > 1) {
                duke.sendMessage("I'm not sure which one to delete",
                        new Widget(Arrays.stream(tasksFound)
                                .map(Task::createLabelWidget).collect(Collectors.toList()))
                );
                throw new InvalidCommandException(
                        String.format("There was an ambiguity with the above %d tasks",
                                tasksFound.length));
            }

            return duke.getTaskList().indexOf(tasksFound[0]);
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

        duke.save();
    }

    private Task deleteAt(int index) {
        assert index >= 0 && index < duke.getTaskList().getSize();
        return duke.getTaskList().remove(index);
    }

    private void askToDeleteAllTasks() {
        duke.attachState(this);
        duke.sendMessage("Are you sure you want to delete all tasks?");
    }

    private void deleteAllTasks() {
        duke.getTaskList().clear();
    }
}
