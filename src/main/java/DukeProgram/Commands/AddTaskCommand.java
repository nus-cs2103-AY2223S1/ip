package DukeProgram.Commands;

import DukeProgram.Deadline;
import DukeProgram.Event;
import DukeProgram.Facilities.TaskList;
import DukeProgram.Task;
import DukeProgram.ToDo;
import Exceptions.InvalidCommandException;
import Exceptions.InvalidJobException;
import Exceptions.JobNameException;
import Utilities.StringUtilities;

import java.util.Arrays;
import java.util.stream.Collectors;

import static DukeProgram.UI.UserInterface.printInStyle;

public class AddTaskCommand extends Command {

    private final String[] fullCommandParameters;

    public AddTaskCommand(String[] fullCommandParameters) {
        this.fullCommandParameters = fullCommandParameters;
    }

    @Override
    public boolean execute() {
        try {
            return parse().execute();
        } catch (InvalidCommandException ex) {
            return true;
        }
    }

    private Command parse() throws InvalidCommandException {
        return parse("");
    }

    @Override
    public Command parse(String commandString) throws InvalidCommandException {
        Task task;
        String[][] nameAndDate;

        switch (fullCommandParameters[1]) {
        case "todo":
            return new CreateToDoTask();

        case "deadline":
            return new CreateDeadlineTask();

        case "event":
            return new CreateEventTask();

        default:
            throw new InvalidCommandException();
        }
    }

    private static String concatName(String[] input) throws JobNameException {
        String name = Arrays.stream(input).skip(2).collect(Collectors.joining(" "));
        if (name.equals("")) {
            throw new JobNameException(input[0]);
        }
        return name;
    }

    private abstract static class CreateTaskCommand extends Command {

        protected Task task;

        @Override
        public boolean execute() {
            printInStyle(
                    "Got it. I've added this task:",
                    task.toString(),
                    String.format("Now you have %d tasks in the list", TaskList.current().size()));
            return true;
        }

        @Override
        public abstract Command parse(String commandString) throws InvalidCommandException;
    }

    private class CreateToDoTask extends CreateTaskCommand {

        @Override
        public boolean execute() {
            try {
                task = new ToDo(concatName(fullCommandParameters));
                TaskList.current().add(task);
                super.execute();

            } catch (JobNameException ex) {
                printInStyle(ex.getMessage());
            }
            return true;
        }

        @Override
        public Command parse(String commandString) throws InvalidCommandException {
            throw new InvalidCommandException(this, commandString);
        }
    }


    private class CreateDeadlineTask extends CreateTaskCommand {
        @Override
        public boolean execute() {
            String[][] nameAndDate = StringUtilities
                    .splitStringArray(fullCommandParameters, "/by");

            if (nameAndDate.length != 2) {
                printInStyle("Please use /by to set a time");
                return true;
            }

            try {
                task = new Deadline(concatName(nameAndDate[0]),
                        String.join(" ", nameAndDate[1]));
                TaskList.current().add(task);
                super.execute();

            } catch (JobNameException ex) {
                printInStyle(ex.getMessage());
            }
            return true;
        }

        @Override
        public Command parse(String commandString) throws InvalidCommandException {
            throw new InvalidCommandException(this, commandString);
        }
    }

    private class CreateEventTask extends CreateTaskCommand {
        @Override
        public boolean execute() {
            String[][] nameAndDate = StringUtilities.splitStringArray(fullCommandParameters, "/at");
            if (nameAndDate.length != 2) {
                printInStyle("Please use /at to set a time");
                return true;
            }

            try {
                task = new Event(concatName(nameAndDate[0]),
                        String.join(" ", nameAndDate[1]));
                TaskList.current().add(task);
                super.execute();

            } catch (JobNameException ex) {
                printInStyle(ex.getMessage());
            }
            return true;
        }

        @Override
        public Command parse(String commandString) throws InvalidCommandException {
            throw new InvalidCommandException(this, commandString);
        }
    }
}
