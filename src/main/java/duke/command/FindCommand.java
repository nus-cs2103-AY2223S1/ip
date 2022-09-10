package duke.command;

import duke.exception.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String keyword = inputArray[1];
        TaskList temp = new TaskList();
        int index = 1;
        if (inputArray[1].isEmpty()) {
            throw new DukeException("OOPS!!! Please input something for me to find.\n");
        }
        for (Task t : taskList.getTaskList()) {
            if (t.getDescription().contains(keyword)) {
                temp.add(t);
            }
        }
        if (temp.size() == 0) {
            throw new DukeException("No matching tasks in your list\n");
        }
        this.response = "Here are the matching tasks in your list:\n";
        for (Task t : temp.getTaskList()) {
            this.response += index + "." + t.toString() + "\n";
            index++;
        }
    }
}
