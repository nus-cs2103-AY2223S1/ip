public class MarkCommand extends Command {
    private String[] splitCommands;
    private TaskList tasks;
    private Ui ui;

    public MarkCommand(String[] splitCommands, TaskList tasks, Ui ui) {
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
        if (splitCommands.length <= 1) {
            throw new DukeException("your command is incomplete." +
                    "\nPlease use the [help] command to check the proper usage of [mark].");
        } else if (splitCommands.length > 2) {
            throw new DukeException("your command has too many arguments." +
                    "\nPlease use the [help] command to check the proper usage of [mark].");
        } else if (isNumber(splitCommands[1])) {
            int taskId = Integer.parseInt(splitCommands[1]) - 1;
            if (tasks.size() <= taskId || taskId < 0) {
                throw new DukeException("that task you want to mark does not exist. "
                        + "Use the [list] command to check what tasks are available.");
            } else {
                tasks.getTask(taskId).markDone();
                ui.mark(tasks.getTask(taskId));
            }
        } else {
            throw new DukeException("your command is incorrect." +
                "\nPlease use the [help] command to check the proper usage of [mark].");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
