package duke.command;

import duke.MessagePrinter;
import duke.Storage;
import duke.TaskList;

public class GreetCommand extends Command {
    protected GreetCommand() {
        super(Action.GREET);
    }

    @Override
    public void execute(TaskList taskList, MessagePrinter messagePrinter, Storage storage) {
        String LOGO = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String HELLO_MESSAGE = "Hello! I'm Duke \n" + "What can I do for you?";
        messagePrinter.printMessage("Hello from\n" + LOGO + "\n" + HELLO_MESSAGE);
    }

    @Override
    public String getFormat() {
        return "greet";
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
        return obj instanceof GreetCommand;
    }
}
