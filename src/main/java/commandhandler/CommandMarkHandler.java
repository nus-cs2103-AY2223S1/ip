package commandhandler;

import data.TaskList;
import data.tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class CommandMarkHandler extends CommandHandler {

    CommandMarkHandler(TaskList taskList) {
        super(taskList);
    }

    private boolean toMark(List<String> commandTokens) {
        return commandTokens.get(0).toUpperCase().equals(Command.MARK.toString());
    }

    @Override
    public boolean validateCommand(List<String> commandTokens) {
        // There must be a task index followed by the `mark`/`unmark` command
        return commandTokens.size() == 2;
    }

    @Override
    public List<String> run(List<String> commandTokens) {
        List<String> responseList = new ArrayList<>();

        String taskIdxStr = commandTokens.get(1);
        try {
            int taskIdx = Integer.parseInt(taskIdxStr);
            if (taskIdx < 0 || taskIdx > taskList.size()) {
                responseList.add("Invalid task selected!");
            } else {
                Task task = taskList.getTask(taskIdx - 1);
                if (toMark(commandTokens)) {
                    task.mark();
                    responseList.add("Nice! I've mark this task as done:");
                } else {
                    task.unmark();
                    responseList.add("OK, I've marked this task as not done yet:");
                }
                responseList.add(String.format("\t%s", task));
            }
        } catch (NumberFormatException error) {
            responseList.add("Invalid task index!");
        }

        return responseList;
    }
}
