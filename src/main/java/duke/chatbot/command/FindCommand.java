package duke.chatbot.command;

import duke.chatbot.data.task.Task;
import duke.chatbot.data.task.TaskList;

import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public FindCommand(List<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public CommandResult execute() {
        List<String> message = new ArrayList<>();
        String query = arguments.get(0);

        TaskList filteredTaskList = taskList.filterTaskListBySubstring(query);

        for (int entry = 1; entry <= filteredTaskList.size(); entry++) {
            Task task = filteredTaskList.get(entry);
            message.add(entry + ". " + task.toString());
        }

        return new CommandResult(message);
    }
}
