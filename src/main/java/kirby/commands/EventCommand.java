package kirby.commands;

import java.io.IOException;

import kirby.Storage;
import kirby.TaskList;
import kirby.exceptions.KirbyMissingArgumentException;
import kirby.tasks.Event;
import kirby.ui.Ui;

/**
 * EventCommand class handles the command to create an Event task.
 */
public class EventCommand extends Command {
    private String inputString;

    /**
     * Constructor for the class EventCommand.
     *
     * @param inputString Arguments of a command.
     */
    public EventCommand(String inputString) {
        this.inputString = inputString;
    }

    /**
     * {@inheritDoc}
     * Creates an Event task if arguments are valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws KirbyMissingArgumentException {
        if (!inputString.contains("/at") || inputString.length() - 1 < inputString.indexOf("/at") + 4 || inputString.indexOf(" /at") <= inputString.indexOf("event") + 6) {
            throw new KirbyMissingArgumentException("event");
        }
        String taskName = inputString.substring(inputString.indexOf("event") + 6, inputString.indexOf(" /at"));
        String at = inputString.substring(inputString.indexOf("/at") + 4);
        Event event = new Event(taskName, at);
        tasks.addTask(event);
        try {
            storage.writeTask(tasks.getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks.addTaskString(event);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
