package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

public class ReadCommand extends Command {
    protected ReadCommand() {
        super(Action.READ);
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        taskList.loadFrom(storage.read());
        int size = taskList.size();
        String temp = size == 1 ? "task has" : "tasks have";
        messagePrinter.printMessage("Your " + size + " " + temp + " been loaded successfully\n"
                + "Type [list] to view your tasks");
    }

    @Override
    public String getFormat() {
        return "read";
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
        if (obj instanceof ReadCommand) {
            return true;
        }
        return false;
    }
}
