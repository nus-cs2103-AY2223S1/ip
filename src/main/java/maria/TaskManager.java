package maria;

import maria.command.CommandExecutor;
import maria.task.TaskList;
import maria.util.StorageConverter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Acts as the interface for all the tasks related logic in the program.
 */
public class TaskManager {

    private final TaskList taskList;
    private final Storage storage;
    private final CommandExecutor commandExecutor;
    private final Queue<String> resultDisplayQueue;

    /**
     * Starts the task manager by reading from the storage.
     */
    public TaskManager() {

        this.storage = new Storage("tasks.mariadata");
        this.taskList = new TaskList(this.storage);
        this.resultDisplayQueue = new LinkedList<>();
        this.commandExecutor = new CommandExecutor(this);
        StorageConverter.stringToTasks(this);

    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Storage getStorage() {
        return this.storage;
    }

    public CommandExecutor getCommandExecutor() {
        return this.commandExecutor;
    }

    public Queue<String> getResultDisplayQueue() {
        return this.resultDisplayQueue;
    }
}
