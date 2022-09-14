package duke.util.command;

import duke.task.TaskList;
import duke.util.StoredTasks;

public class CommandList extends Command {

    public CommandList(String command) {
        super(command);
    }

    @Override
    public String handleCommand(TaskList taskList, StoredTasks storedTasks) {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            listString.append(" ").append(String.valueOf(i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return "Stop slacking liao, see got so much stuff to do:\n" + listString.toString();
    }
}
