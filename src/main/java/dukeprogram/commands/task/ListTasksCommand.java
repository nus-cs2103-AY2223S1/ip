package dukeprogram.commands.task;

import static dukeprogram.ui.UserInterface.printInStyle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import dukeprogram.DatedJob;
import dukeprogram.Task;
import dukeprogram.UiMessage;
import dukeprogram.commands.Command;
import dukeprogram.facilities.TaskList;
import dukeprogram.ui.UserInterface;
import exceptions.InvalidCommandException;

/**
 * ListTasksCommand is capable of listing all the tasks stored in the current task list
 * only if a valid name of an existing task list is provided to be parsed
 */
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
            UserInterface.printInStyle(Arrays.stream(TaskList.current()
                    .getAllTasks())
                    .filter(e -> e instanceof DatedJob)
                    .sorted(Comparator.comparing(e -> ((DatedJob) e).getDate()))
            );
            return true;
        }
    }

    private static class ListTasksByAlphabetCommand extends Command {
        @Override
        public boolean execute() {
            printInStyle(Arrays.stream(TaskList.current().getAllTasks())
                    .sorted(Comparator.comparing(Task::getName))
            );
            return true;
        }
    }
}
