package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.InsufficientArgumentsException;
import tasklist.TaskList;

public class FindCommand extends Command {
    String[] args;

    public FindCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        if (args.length < 1) {
            throw new InsufficientArgumentsException("Find command");
        }
    }

    @Override
    public String execute(TaskList taskList) {
        TaskList results = new TaskList();
        String target = this.args[0];

        for (int i = 0; i < taskList.size(); i++) {
            String taskRepresentation = taskList.get(i).toString();
            if (taskRepresentation.contains(target)) {
                results.addTask(taskList.get(i));

            }
        }

        return ChatResponse.returnChatFindResults(results);
    }
}
