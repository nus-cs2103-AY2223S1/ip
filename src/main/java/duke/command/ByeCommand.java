package duke.command;

import duke.task.TaskList;
import duke.util.UI;

public class ByeCommand extends Command {
    private final String BYE_MESSAGE = "Bye! Hope to see you soon!";

    public ByeCommand(String[] args) {
        super(CommandType.BYE, args);
    }

    @Override
    public void execute(TaskList tasks) {
        UI.print(BYE_MESSAGE);
    }
}
