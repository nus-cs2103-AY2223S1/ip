package dukeprogram.command;

import java.util.Arrays;
import java.util.stream.Collectors;

import dukeprogram.Deadline;
import dukeprogram.Duke;
import dukeprogram.Event;
import dukeprogram.InternalAction;
import dukeprogram.Task;
import dukeprogram.ToDo;
import dukeprogram.facilities.TaskList;
import exceptions.JobNameException;
import utilities.StringUtilities;


/**
 * Adds a task to the task list
 */
public class AddTaskCommand extends Command {

    @Override
    protected InternalAction onEnter() {
        return new InternalAction("I can help you add tasks");
    }

    @Override
    protected InternalAction onStay() {
        return new InternalAction("Is there another task you wanted to add?");
    }

    @Override
    public InternalAction onParse(String input) {
        String[] separatedCommands = input.split(" ");
        if (separatedCommands.length < 2) {
            return new InternalAction("Hmm, you need to tell me what you want to add...");
        }

        String[][] nameAndDate;
        Task taskAdded;

        switch (separatedCommands[1]) {
        case "todo":
            try {
                taskAdded = new ToDo(concatName(separatedCommands));
                TaskList.current().add(taskAdded);
                break;
            } catch (JobNameException e) {
                return new InternalAction("My bro, I cannot add a task with no name...");
            }

        case "event":
            nameAndDate = StringUtilities
                    .splitStringArray(separatedCommands, "/at");

            if (nameAndDate.length != 2) {
                return new InternalAction("Please use /at to set a time");
            }

            try {
                taskAdded = new Event(concatName(nameAndDate[0]),
                        String.join(" ", nameAndDate[1]));

                TaskList.current().add(taskAdded);
                break;
            } catch (JobNameException e) {
                return new InternalAction("My bro, I cannot add a task with no name...");
            }

        case "deadline":
            nameAndDate = StringUtilities
                    .splitStringArray(separatedCommands, "/by");

            if (nameAndDate.length != 2) {
                return new InternalAction("Please use /by to set a time");
            }

            try {
                taskAdded = new Deadline(concatName(nameAndDate[0]),
                        String.join(" ", nameAndDate[1]));

                TaskList.current().add(taskAdded);
                break;
            } catch (JobNameException e) {
                return new InternalAction("My bro, I cannot add a task with no name...");
            }

        default:
            return new InternalAction("Hmm, I don't think that's a valid task type...");
        }

        return new InternalAction(
                String.format("Got it mate... Here's your task added: \n%s", taskAdded),
                Duke::exitCurrentState);
    }

    @Override
    public Command onExit() {
        return new AccessTasksCommand();
    }

    private static String concatName(String[] input) throws JobNameException {
        String name = Arrays.stream(input).skip(2).collect(Collectors.joining(" "));
        if (name.equals("")) {
            throw new JobNameException(input[0]);
        }
        return name;
    }
}
