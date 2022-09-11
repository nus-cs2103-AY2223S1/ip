package maria.command;

import maria.TaskManager;

/**
 * Executes all commands in Maria.
 */
public class CommandExecutor {

    private TaskManager taskManager;

    public CommandExecutor(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void executeCommand(Command command) {
        this.taskManager.getResultDisplayQueue().add(command.execute(taskManager));
    }

}
