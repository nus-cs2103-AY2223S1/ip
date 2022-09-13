package duke.util.command;

import duke.task.Deadlines;
import duke.task.Task;
import duke.task.TaskList;
import duke.util.DateAndTimeFormatter;
import duke.util.StoredTasks;

public class CommandDeadline extends Command {

    public CommandDeadline(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        String[] words = command.split(" /by", 2);
        Task deadline = new Deadlines(words[0], words[1], DateAndTimeFormatter.validateAndParse(words[1]));
        taskList.add(deadline);
        return "Got it. I've added this task:\n" + deadline + "\nNow you have " +
                String.valueOf(taskList.size()) + " tasks in the list.";
    }
}
