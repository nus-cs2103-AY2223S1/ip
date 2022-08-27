package ip.command;

import ip.Storage;
import ip.TaskList;
import ip.exception.BadDeadline;
import ip.exception.BadTimespan;
import ip.exception.MissingDescription;
import ip.task.Deadline;
import ip.task.Event;
import ip.task.ToDo;

import java.util.Scanner;

public class AddCommand extends Command {
    private String commandGiven;
    private Scanner options;

    public AddCommand(String commandGiven, Scanner options) {
        this.commandGiven = commandGiven;
        this.options = options;
    }

    @Override
    public void execute(TaskList taskList) throws MissingDescription, BadTimespan, BadDeadline {
        switch (commandGiven) {
        case "todo":
            taskList.add(new ToDo(options));
            break;
        case "deadline":
            taskList.add(new Deadline(options));
            break;
        case "event":
            taskList.add(new Event(options));
            break;
        }
    }
}
