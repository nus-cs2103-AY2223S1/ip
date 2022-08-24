package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

public class ListCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        System.out.println("Here are the tasks in your list:");

        if (taskList.size() == 0) {
            System.out.println("Oops, there are no tasks. Please add tasks to the list!");
        } else {
            for (int i = 0; i < taskList.size(); ++i) {
                System.out.printf(" %d. %s\n", i + 1, taskList.get(i));
            }
        }

        storage.save(taskList);

    }


    @Override
    public boolean isEnd() {
        return false;
    }
}
