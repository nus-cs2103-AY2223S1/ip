package command;

import duke.TaskList;
import duke.Ui;
import task.Deadlines;

import java.text.ParseException;

/**
 * An abstract class that represents DeadlineCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class DeadlineCommand extends Command{

    private TaskList taskList;
    private String input;
    private String taskAndDate;
    private Ui ui;

    /**
     * Constructor for DeadlineCommand Object
     */
    public DeadlineCommand(TaskList taskList, String input, String taskAndDate) {
        this.taskList = taskList;
        this.input = input;
        this.taskAndDate = taskAndDate;
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed DeadlineCommand
     * @return a string after the execution of DeadlineCommand
     */
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
