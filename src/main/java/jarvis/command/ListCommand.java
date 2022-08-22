package jarvis.command;

import jarvis.storage.Storage;
import jarvis.task.TaskList;

public class ListCommand extends Command {
    public ListCommand(String command) {
        super(command);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.toString();
    }
}
