package duke.command;

import duke.task.Deadline;
import duke.oop.Storage;
import duke.oop.TaskList;
import duke.oop.Ui;

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
        storage.update((taskList.getTasks()));
        return "Got it. I've added this task:\n" + newDeadline.toString()
                + "\nNow you have" + taskList.getTasks().size() + " tasks in the list.";
    }
}
