package command;

import duke.TaskList;
import duke.Ui;
import task.Deadlines;

import java.text.ParseException;

public class DeadlineCommand extends Command{

    private TaskList taskList;
    private String input;
    private String taskAndDate;
    private Ui ui;

    public DeadlineCommand(TaskList taskList, String input, String taskAndDate) {
        this.taskList = taskList;
        this.input = input;
        this.taskAndDate = taskAndDate;
        this.ui = new Ui();
    }

    @Override
    public String execute() throws ParseException {
        String[] findTask = input.split(taskAndDate);
        String theTask = findTask[1].split(" /")[0];
        String actualTask = theTask;
        String[] splitStr2 = input.split("/by ");
        String date = splitStr2[1];
        Deadlines deadline = new Deadlines(actualTask, splitStr2[1]);
        taskList.add(deadline);
        return ui.deadlineMessage(deadline, taskList.size());
    }
}
