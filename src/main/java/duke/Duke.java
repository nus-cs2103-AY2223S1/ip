package duke;

import java.nio.file.Path;

import duke.command.CommandException;
import duke.command.CommandFactory;
import duke.command.handler.CommandHandler;
import duke.command.response.CommandResponse;
import duke.data.TaskList;
import duke.data.storage.Storage;
import duke.data.storage.StorageException;

public class Duke {

    private final Storage<TaskList> taskListStorage;
    private TaskList taskList;
    private boolean hasTerminated;

    public Duke(Path cachePath) {
        this.taskListStorage = new Storage<>(cachePath);
        this.taskList = new TaskList();
        this.hasTerminated = false;
    }

    public void loadCache() throws StorageException {
        this.taskList = taskListStorage.load(new TaskList());
    }

    public boolean hasTerminated() {
        return hasTerminated;
    }

    public CommandResponse getResponse(String commandStr)
            throws CommandException, StorageException {
        CommandHandler commandHandler = CommandFactory.getCommandHandler(commandStr);
        CommandResponse commandResponse = commandHandler.run(taskList);

        hasTerminated = commandResponse.isTriggerTerminate();
        if (commandResponse.isTriggerSave()) {
            taskListStorage.save(taskList);
        }

        return commandResponse;
    }

    public String getWelcomeMessage() {
        return String.format("%s\n%s", "Hi I'm Duke", "What can I do for you?");
    }
}
