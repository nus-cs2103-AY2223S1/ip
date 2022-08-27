package ip.command;

import ip.Storage;
import ip.TaskList;
import ip.exception.IndexNotSpecified;
import ip.exception.NoTaskFound;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class EditCommand extends Command {
    private String commandGiven;
    private Scanner options;

    public EditCommand(String commandGiven, Scanner options) {
        this.commandGiven = commandGiven;
        this.options = options;
    }

    @Override
    public void execute(TaskList taskList) throws IndexNotSpecified, NoTaskFound {
        int index;
        try {
            index = options.nextInt();
        } catch (NoSuchElementException e) {
            throw new IndexNotSpecified();
        }
        switch (commandGiven) {
        case "mark":
            taskList.mark(index - 1);
            break;
        case "unmark":
            taskList.unmark(index - 1);
            break;
        case "delete":
            taskList.delete(index - 1);
            break;
        }
    }
}
