package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandDelete extends Command {

    public CommandDelete(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        Integer taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
        StringBuilder beforeDeleteString = new StringBuilder();
        beforeDeleteString.append("Ok boss! I've removed this task liao:" + "\n")
                .append(taskList.get(taskNo)).append("\n");
        int i = taskNo;
        taskList.remove(i);
        String afterDeleteString = "You have " + String.valueOf(taskList.size()) + " tasks left boss!" +
                ".\n";
        return beforeDeleteString.append(afterDeleteString).toString();
    }
}
