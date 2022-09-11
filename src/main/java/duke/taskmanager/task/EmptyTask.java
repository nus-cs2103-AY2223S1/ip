package duke.taskmanager.task;

import duke.chatbot.commands.exceptions.EmptyTaskException;

/**
 * Task that is empty.
 */
public class EmptyTask extends Task {
    /**
     * Creates an empty task with an empty task name. Used for exception handling.
     */
    public EmptyTask() throws EmptyTaskException {
        super("", "");
    }

    /**
     * Does nothing
     * @param arguments
     * @throws Exception
     */
    @Override
    public void update(String arguments) throws Exception {
    }
}
