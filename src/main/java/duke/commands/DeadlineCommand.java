package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.Deadline;
import duke.ui.Ui;

public class DeadlineCommand extends Command{

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] splitInput = ui.userString().split(" ");
        System.out.println("Got it. I've added this task");
        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            if (splitInput[i].equals("/by")) break;
            deadline.append(" " + splitInput[i]);
        }

        String date = ui.userString().split("/by")[1].trim();
        Deadline deadlineTask = new Deadline(deadline.toString(), date);
        taskList.add(deadlineTask);
        System.out.printf("\t %s\n", deadlineTask);
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());

        storage.save(taskList);
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
