package duke.util.command;

import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.SaveTasks;

public class CommandEvent extends Command {

    public CommandEvent(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, SaveTasks saveTasks) {
        String[] words = command.split(" /at", 2);
        Task event = new Event(words[0], words[1]);
        taskList.add(event);
        return "Got it. I've added this task:\n" + event + "\nNow you have " +
                String.valueOf(taskList.size()) + " tasks in the list.";
    }
}
