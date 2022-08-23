package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

public class ExitCommand extends Command {
    protected ExitCommand() {
        super(Action.EXIT);
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String FAREWELL_MESSAGE = "Bye. Hope to see you again soon!";
        messagePrinter.printMessage(FAREWELL_MESSAGE);
    }

    @Override
    public String getFormat() {
        return "bye";
    }

    @Override
    public boolean isTerminated() {
        return true;
    }
}
