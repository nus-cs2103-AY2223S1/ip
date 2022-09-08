package duke.util.command;

import duke.task.TaskList;
import duke.util.SaveTasks;

public class CommandMark extends Command {

    public CommandMark(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, SaveTasks saveTasks) {
        Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
        taskList.get(taskNo).markAsDone();
        return "Nice! I've marked this task as done:\n"
                + taskList.get(taskNo);
    }

}
