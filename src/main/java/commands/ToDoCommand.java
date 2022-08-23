package commands;

import tasklist.TaskList;
import tasks.ToDo;
import common.Ui;
import dukeexceptions.DukeException;
import dukeexceptions.MissingDescriptionException;

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
