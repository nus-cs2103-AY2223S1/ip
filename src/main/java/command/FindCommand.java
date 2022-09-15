package command;

import duke.TaskList;
import duke.Ui;
import task.Events;
import task.Task;
import task.ToDos;

import java.text.ParseException;
import java.util.function.Consumer;

public class FindCommand extends Command{

    private TaskList taskList;
    private String input;
    private Ui ui;

    public FindCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
        this.ui = new Ui();
    }

    @Override
    public String execute() throws ParseException {
        String[] findTask = input.split(" ");
        String theTask = findTask[1];
        String actualTask = theTask;
        TaskList findList = new TaskList();
        String taskName = actualTask;
        Consumer<? super Task> consumer = x -> {
            if (x.toString().contains(taskName)) {
                findList.add(x);
            }
        };

        taskList.forEach(consumer);
        return ui.findMessage() + findList.list();
    }
}
