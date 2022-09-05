package Command;

import duke.Deadline;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class AddDeadlineCommand extends Command{
    private String deadlineTask;
    private String deadline;

    public AddDeadlineCommand(String deadlineTask, String deadline) {
        this.deadlineTask = deadlineTask;
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        Deadline newDeadline = new Deadline(deadlineTask, deadline);
        taskList.getTasks().add(newDeadline);
        return "\"Got it. I've added this task:\" + \"\\n\" + tasks.get(numOfTasks - 1).toString()\n" +
                "                \"\\n\" + \"Now you have \" + numOfTasks + \" tasks in the list.\"";
    }
}
