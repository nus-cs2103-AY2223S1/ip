package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

public class DoNothingCommand extends Command {
    protected DoNothingCommand() {
        super(Action.DONOTHING);
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
//            do nothing
    }

    @Override
    public String getFormat() {
        return "";
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof DoNothingCommand;
    }
}

