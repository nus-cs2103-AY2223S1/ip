package dukeprogram.commands.task;

import dukeprogram.commands.Command;
import dukeprogram.DatedJob;
import dukeprogram.facilities.TaskList;
import dukeprogram.Task;
import dukeprogram.UiMessage;
import exceptions.InvalidCommandException;
import java.util.Comparator;
import java.util.stream.IntStream;

import static dukeprogram.ui.UserInterface.printInStyle;

public class ListTasksCommand extends Command {

    private final String[] fullCommandParameters;

    public ListTasksCommand(String[] fullCommandParameters) {
        this.fullCommandParameters = fullCommandParameters;
    }

    @Override
    public boolean execute() {
        if (fullCommandParameters.length == 1) {
            TaskList currentTaskList = TaskList.current();
            printInStyle(IntStream
                    .range(0, currentTaskList.getSize())
                    .mapToObj(i -> String.format("%d: %s", i + 1, currentTaskList.get(i).toString()))
            );
            return true;
        }

        try {
            return parse(fullCommandParameters[1]).execute();
        } catch (InvalidCommandException e) {
            printInStyle(e.getUiMessage().toString());
        }

        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        switch (commandString) {
        case "date":
            return new ListTasksByDateCommand();

        case "alphabet":
            return new ListTasksByAlphabetCommand();

        default:
            throw new InvalidCommandException(this, commandString,
                    new UiMessage(
                            String.format("Sorry! I don't know how to sort by %s", commandString)
                    )
            );
        }
    }

    private static class ListTasksByDateCommand extends Command {

        @Override
        public boolean execute() {
            printInStyle(TaskList.current()
                    .stream()
                    .filter(e -> e instanceof DatedJob)
                    .sorted(Comparator.comparing(e -> ((DatedJob)e).getDate()))
            );
            return true;
        }
    }

    private static class ListTasksByAlphabetCommand extends Command {
        @Override
        public boolean execute() {
            printInStyle(TaskList.current()
                    .stream()
                    .sorted(Comparator.comparing(Task::getName))
            );
            return true;
        }
    }
}
