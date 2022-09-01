package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.ui.Ui;

/**
 * Command to create a deadline.
 */
public class DeadlineCommand extends Command {

    /**
     * Executes the deadline-creating command.
     * @param taskList
     * @param ui User Interface of the to-do-list.
     * @param storage Storage option.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        String totalString = "";

        String[] splitInput = ui.userString().split(" ");

        totalString += "Got it. I've added this task\n";

        StringBuilder deadline = new StringBuilder();

        for (int i = 1; i < splitInput.length; ++i) {
            if (splitInput[i].equals("/by")) break;
            deadline.append(" " + splitInput[i]);
        }

        String date = ui.userString().split("/by")[1].trim();
        Deadline deadlineTask = new Deadline(deadline.toString(), date);
        taskList.add(deadlineTask);

//        System.out.printf("\t %s\n", deadlineTask);
        totalString += String.format("\t %s\n", deadlineTask);
        totalString += String.format("Now you have %d tasks in the list. \n", taskList.size());
        System.out.printf("Now you have %d tasks in the list.\n", taskList.size());

        storage.save(taskList);
        return totalString;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
