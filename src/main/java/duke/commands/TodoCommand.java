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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String userInput = ui.userString();
        String[] splitInput = userInput.split(" ");

        System.out.println("Got it. I've added this task");
        StringBuilder todo = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            todo.append(' ');
            todo.append(splitInput[i]);
        }

        ToDo todoTask = new ToDo(todo.toString());
        taskList.add(todoTask);
        System.out.printf("\t %s\n", todoTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());
        storage.save(taskList);

    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
