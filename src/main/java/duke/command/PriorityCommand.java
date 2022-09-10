package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.task.Task;

public class PriorityCommand extends Command{
    private final String userInput;

    public PriorityCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String message = "";

        if (userInput.split(" ").length < 3) {
            throw new DukeException("OOPS!!! The priority command cannot have parameters. Example command is priority 1 high");
        }
        String index = userInput.split(" ")[1];
        String priorityString = userInput.split(" ")[2];
        Task task = taskList.updateTaskPriority(Integer.parseInt(index), priorityString);
        message += "OK, I've marked set the priority of the task to " + task.getPriorityString() + "\n";
        message += task.toString() + "\n";
        storage.saveTaskList(taskList);
        return message;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
