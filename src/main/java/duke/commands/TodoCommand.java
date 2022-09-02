package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Command to create a todo task.
 */
public class TodoCommand extends Command {

    /**
     * Executes the command.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String userInput = ui.userString();
        String[] splitInput = userInput.split(" ");
        StringBuilder todo = new StringBuilder();
        StringBuilder sb = new StringBuilder();

        sb.append("Got it. I've added this task\n");

        for (int i = 1; i < splitInput.length; ++i) {
            todo.append(' ');
            todo.append(splitInput[i]);
        }

        ToDo todoTask = new ToDo(todo.toString());
        taskList.add(todoTask);
        sb.append(String.format("\t %s\n", todoTask));
        sb.append(String.format("Now you have %d tasks in the list.\n", taskList.size()));
        storage.save(taskList);
        return sb.toString();

    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
