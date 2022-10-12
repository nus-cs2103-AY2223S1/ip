package duke.commands;

import duke.DukeException;
import duke.task.Task;
import duke.TaskList;
import duke.Storage;

import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class SnoozeCommand extends Command {
    private final String[] arguments;

    public SnoozeCommand(String[] args) {
        this.arguments = args;
    }

    @Override
    public String execute(Storage storage, TaskList tl) throws DukeException, DateTimeParseException {
        String[] taskDesc = Arrays.copyOfRange(arguments, 1, arguments.length);
        taskDesc = String.join(" ", taskDesc).split(" /to ");
        if (taskDesc.length < 2) {
            throw new DukeException("You're missing some details!\n"
                    + "\nThe command format should be: snooze TASK_NUMBER /to DATETIME"
                    + "\nThe format of the DATETIME should be as follows:"
                    + "\nFor Deadlines --> dd/MM/yyyy[ HHmm]"
                    + "\nFor Events --> dd/MM/yyyy HHmm");
        }

        String response;
        try {
            int taskNum = Integer.parseInt(taskDesc[0]) - 1;
            response = tl.snoozeTask(taskNum, taskDesc[1]);
            storage.refreshList(tl.getTasks());
        } catch (IndexOutOfBoundsException indexErr) {
            response = "This task number does not exist in the list!";
        } catch (NumberFormatException parseErr) {
            response = "Is that even a number?! Try entering an actual number next time";
        }
        return response;
    }
}
