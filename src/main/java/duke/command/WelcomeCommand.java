package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

public class WelcomeCommand extends Command {
    private static final String GREETING = "Hello! I'm Duke.\nWhat can I do for you?";

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return GREETING;
    }
}
