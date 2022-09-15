package command;

import duke.TaskList;
import duke.Ui;
import task.Events;

import java.text.ParseException;

/**
 * An abstract class that represents EventCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class EventCommand extends Command {

    private TaskList taskList;
    private String input;
    private String taskAndDate;
    private Ui ui;

    /**
     * Constructor for EventCommand Object
     */
    public EventCommand(TaskList taskList, String input, String taskAndDate) {
        this.taskList = taskList;
        this.input = input;
        this.taskAndDate = taskAndDate;
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed EventCommand
     * @return a string after the execution of EventCommand
     */
    @Override
    public String execute() throws ParseException {
        String[] findTask = input.split(taskAndDate);
        String theTask = findTask[1].split(" /")[0];
        String actualTask = theTask;
        String[] splitStr2 = input.split("/at ");
        String date = splitStr2[1];
        Events event = new Events(actualTask, splitStr2[1]);
        taskList.add(event);
        return ui.eventMessage(event, taskList.size());
    }
}

