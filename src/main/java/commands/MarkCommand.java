package commands;

import common.ChatResponse;
import dukeexceptions.DukeException;
import dukeexceptions.IllegalIndexException;
import tasklist.TaskList;

public class MarkCommand extends Command {
    private final String[] args;

    public MarkCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        if (args.length < 1) {
            throw new DukeException("Missing index!");
        }
        try {
            int index = Integer.parseInt(args[0]) - 1;
            if (index < 0) {
                throw new IllegalIndexException();
            }
        } catch (NumberFormatException e) {
            throw new DukeException(e.toString());
        }
    }

    @Override
    public String execute(TaskList taskList) {
        int index = Integer.parseInt(args[0]) - 1;
        taskList.markTask(index);
        return ChatResponse.returnChatMarkTask(taskList.get(index));
    }
}