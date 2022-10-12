package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Storage;
import duke.task.Event;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class AddEventCommand extends Command {
    private final String[] arguments;

    public AddEventCommand(String[] args) {
        this.arguments = args;
    }

    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException, DateTimeParseException {
        String[] taskDesc = Arrays.copyOfRange(arguments, 1, arguments.length);
        taskDesc = String.join(" ", taskDesc).split(" /at ");
        System.out.println(taskDesc[0]);
        if (taskDesc.length < 2 || taskDesc[0].equals(" ")) {
            throw new DukeException("You're missing some details!\nRemember to include the description "
                    + "followed by '/at dd/MM/yyyy HHmm'");
        }
        Event newEvent = new Event(taskDesc[0], taskDesc[1]);
        tl.addTask(newEvent);
        String response = "Got it! I have added this task to your list:\n  "
                + newEvent.toString()
                + "\nNow you have " + tl.getSize() + " tasks in the list.";
        storage.refreshList(tl.getTasks());
        return response;
    }
}
