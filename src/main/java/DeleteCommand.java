public class DeleteCommand extends Command {
    private String[] splitCommands;
    private TaskList tasks;
    private Ui ui;

    public DeleteCommand(String[] splitCommands, TaskList tasks, Ui ui) {
        this.splitCommands = splitCommands;
        this.tasks = tasks;
        this.ui = ui;
    }

    private boolean isNumber(String string) {
        char[] numberArray = string.toCharArray();
        for (char c : numberArray) {
            if (c < 48 || c > 57)
                return false;
        }
        return true;
    }

    @Override
    public void execute() throws DukeException { //TODO: ui components
        if (splitCommands.length == 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (splitCommands.length > 2) {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        } else if (isNumber(splitCommands[1])) {
            int taskId = Integer.parseInt(splitCommands[1]) - 1;
            if (tasks.size() <= taskId || taskId < 0) {
                throw new DukeException("that task you want to delete does not exist." +
                        "\nUse the [list] command to check what tasks are available.");
            } else {
                ui.delete(tasks.getTask(taskId), (tasks.size() - 1));
                tasks.remove(taskId);
            }
        } else {
            throw new DukeException("your command is incorrect." +
                    "\nPlease use the [help] command to check the proper usage of [delete].");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
