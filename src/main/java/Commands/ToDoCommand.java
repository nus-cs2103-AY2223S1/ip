package Commands;

import TaskList.TaskList;
import Tasks.ToDo;
import common.Ui;
import dukeExceptions.DukeException;
import dukeExceptions.MissingDescriptionException;

public class ToDoCommand extends Command {
    private String[] args;

    public ToDoCommand(String[] args) {
        this.args = args;
    }

    public static void validateArguments(String[] args) throws DukeException {
        if (args.length == 0) {
            throw new MissingDescriptionException("todo");
        }
    }

    @Override
    public void execute(TaskList taskList) {
        ToDo newToDo = new ToDo(String.join(" ", this.args));
        taskList.addTask(newToDo);
        Ui.printAddTask(newToDo, taskList);
    }
}
