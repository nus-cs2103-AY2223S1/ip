package command;

import duke.TaskList;
import duke.Ui;
import task.ToDos;

import java.text.ParseException;

/**
 * An abstract class that represents TodoCommand which extends Command
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.2
 * @since   2022-9-15
 */
public class TodoCommand extends Command {

    private TaskList taskList;
    private String input;
    private Ui ui;

    /**
     * Constructor for TodoCommand Object
     */
    public TodoCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
        this.ui = new Ui();
    }

    /**
     * Returns a string of the executed TodoCommand
     * @return a string after the execution of TodoCommand
     */
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
