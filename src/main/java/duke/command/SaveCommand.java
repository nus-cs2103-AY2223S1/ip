package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

public class SaveCommand extends Command {
    protected SaveCommand() {
        super(Action.SAVE);
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        storage.write(taskList.toFormattedString());
        int size = taskList.size();
        String temp = size == 1 ? "task has" : "tasks have";
        messagePrinter.printMessage("Your " + size + " " + temp + " been saved successfully");
    }

    @Override
    public String getFormat() {
        return "save";
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
        if (obj instanceof SaveCommand) {
            return true;
        }
        return false;
    }
}
