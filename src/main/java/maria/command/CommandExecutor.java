package maria.command;

import maria.TaskManager;

public class CommandExecutor {

    private TaskManager taskManager;

    public CommandExecutor(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    public void executeCommand(Command command) {
        this.taskManager.getResultDisplayQueue().add(command.execute(taskManager));
    }

}
