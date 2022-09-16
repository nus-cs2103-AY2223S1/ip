package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandMark extends Command {

    public CommandMark(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
        taskList.get(taskNo).markAsDone();
        return "Okay Boss! Limpeh marked this task as done:\n"
                + taskList.get(taskNo);
    }

}
