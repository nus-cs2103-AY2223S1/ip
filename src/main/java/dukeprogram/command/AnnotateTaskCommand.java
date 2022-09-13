package dukeprogram.command;

import java.util.Iterator;

import dukeprogram.Duke;
import dukeprogram.storage.SaveManager;
import exceptions.InvalidCommandException;

/**
 * AnnotateTaskCommand can annotate a task of the current task list of the given Duke instance.
 * This is an abstract class that must have its type of annotate specified.
 */
public abstract class AnnotateTaskCommand extends Command {

    private final boolean isToMark;
    private final String annotationMessage;

    /**
     * Creates a new AnnotateTaskCommand
     * @param duke the instance of duke that spawned this command
     * @param isToMark whether the task is meant to be marked or unmarked
     * @param annotationMessage the message to send to the user after the annotation is successful
     */
    public AnnotateTaskCommand(Duke duke, boolean isToMark, String annotationMessage) {
        super(duke);
        this.isToMark = isToMark;
        this.annotationMessage = annotationMessage;
    }

    /**
     * Annotates the present task indexed by an integer defining its position within the task list
     * @param elements the continued iterator of elements
     * @throws InvalidCommandException if the index does not exist
     */
    @Override
    public void parse(Iterator<String> elements) throws InvalidCommandException {
        int index;
        String element = elements.next();
        try {
            index = Integer.parseInt(element) - 1;
            if (index < 0 || index >= duke.getTaskList().getSize()) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("That was not a valid index within the task list");
        }

        duke.getTaskList().get(index).markJobState(isToMark);
        duke.sendMessage(annotationMessage);
        SaveManager.save("tasklist", duke.getTaskList());
    }
}
