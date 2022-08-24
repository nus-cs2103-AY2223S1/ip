package DukeProgram.Commands;

import DukeProgram.DatedJob;
import DukeProgram.Facilities.TaskList;
import DukeProgram.Task;
import Exceptions.InvalidCommandException;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import static DukeProgram.UI.UserInterface.printInStyle;

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
                    .range(0, currentTaskList.size())
                    .mapToObj(i -> String.format("%d: %s", i + 1, currentTaskList.get(i).toString()))
            );
            return true;
        }

        try {
            return parse(fullCommandParameters[1]).execute();
        } catch (InvalidCommandException e) {
            printInStyle("I didn't understand how you wanted me to list the tasks.");
        }

        return true;
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        switch (commandString) {
        case "-date":
            return new ListTasksByDateCommand();

        case "-alphabet":
            return new ListTasksByAlphabetCommand();

        default:
            throw new InvalidCommandException(this, commandString);
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

        @Override
        public Command parse(String commandString) throws InvalidCommandException {
            throw new InvalidCommandException(this, commandString);
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

        @Override
        public Command parse(String commandString) throws InvalidCommandException {
            return null;
        }
    }
}
