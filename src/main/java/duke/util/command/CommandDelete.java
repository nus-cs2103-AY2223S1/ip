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
        beforeDeleteString.append("Noted. I've removed this task:" + "\n").append(taskList.get(taskNo)).append("\n");
        int i = taskNo;
        taskList.remove(i);
        String afterDeleteString = "Now you have " + String.valueOf(taskList.size()) + " tasks in the list" +
                ".\n";
        return beforeDeleteString.append(afterDeleteString).toString();
    }
}
