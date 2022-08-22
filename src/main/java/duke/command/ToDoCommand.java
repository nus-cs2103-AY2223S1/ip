package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;

public class ToDoCommand extends Command {
    private TaskList taskList;
    private String[] inputArr;

    public ToDoCommand(TaskList taskList, String[] inputArr) {
        this.taskList = taskList;
        this.inputArr = inputArr;
    }

    @Override
    public String action() throws DukeException {
        if (this.inputArr.length < 2) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        ToDo toDo = new ToDo(this.inputArr[1]);
        this.taskList.addTask(toDo);
        return ("Got it. I've added this task: " + "\n"
                + toDo + "\n"
                + "Now you have " + this.taskList.getSize() + " tasks in the list." + "\n");
    }
}