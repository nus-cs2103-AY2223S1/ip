package command;

import duke.TaskList;
import duke.Ui;
import task.Events;
import task.ToDos;

import java.text.ParseException;

public class TodoCommand extends Command{

    private TaskList taskList;
    private String input;
    private Ui ui;

    public TodoCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
        this.ui = new Ui();
    }

    @Override
    public String execute() throws ParseException {
        String[] findTask = input.split(" ");
        String theTask = findTask[1];
        String actualTask = theTask;
        ToDos todo = new ToDos(actualTask);
        taskList.add(todo);
        return ui.todoMessage(todo, taskList.size());
    }
}
