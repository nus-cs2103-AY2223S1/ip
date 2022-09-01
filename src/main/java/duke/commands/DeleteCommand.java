package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Command to delete the task at a particular index.
 */
public class DeleteCommand extends Command {

    /**
     * Command that executes the task deletion.
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {

        StringBuilder sb = new StringBuilder();

        sb.append("Noted. I've removed this task:\n");

        String userInput = ui.userString();
        int index = Integer.parseInt(userInput.split(" ")[1]) - 1;

        sb.append(String.format("\t%s\n", taskList.get(index)));
        taskList.remove(index);

        sb.append(String.format("Now you have %d tasks in the list.\n", taskList.size()));
        storage.save(taskList);
        return sb.toString();
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}