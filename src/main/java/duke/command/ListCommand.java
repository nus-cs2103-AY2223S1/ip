package duke.command;

public class ListCommand extends Command {
    public ListCommand() {
        super.isExit = false;
    }

    @Override
    public void execute() {
        Command.ui.displayTaskList(Command.taskList.getTasks());
    }
}
