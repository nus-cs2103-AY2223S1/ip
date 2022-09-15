package command;

import duke.TaskList;
import duke.Ui;
import task.Events;

import java.text.ParseException;

public class EventCommand extends Command{

    private TaskList taskList;
    private String input;
    private String taskAndDate;
    private Ui ui;

    public EventCommand(TaskList taskList, String input, String taskAndDate) {
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
        String[] splitStr2 = input.split("/at ");
        String date = splitStr2[1];
        Events event = new Events(actualTask, splitStr2[1]);
        taskList.add(event);
        return ui.eventMessage(event, taskList.size());
    }
}

