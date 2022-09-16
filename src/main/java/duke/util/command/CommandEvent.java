package duke.util.command;

import duke.exception.DukeException;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandEvent extends Command {

    public CommandEvent(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks saveTasks) throws DukeException {
        String[] words = command.split(" /at", 2);
        Task event = new Event(words[0], words[1]);
        taskList.add(event);
        return "Swee Chai Butterfly! limpeh added this task:\n" + event + "\nNow you have " +
                String.valueOf(taskList.size()) + " tasks in the list.";
    }
}
