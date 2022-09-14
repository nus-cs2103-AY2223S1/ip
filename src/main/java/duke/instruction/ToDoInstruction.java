package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.support.DukeException;
import duke.tasks.Todo;

/**
 * ToDoInstruction class to initiate a To Do command inputted by the user.
 *
 * @author lauralee
 */
public class ToDoInstruction implements Instruction {

    TaskList taskList;
    String userInput;
    Todo newTask;

    /**
     * Constructor for the ToDoInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public ToDoInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
    }

    @Override
    public String execute() {
        try {
            this.userInput.substring(5);
        } catch (Exception StringIndexOutOfBoundsException) {
            return DukeException.todoException();
        }
        String description = userInput.substring(5);
        this.newTask = new Todo(description);
        this.taskList.addTask(this.newTask);
        return Ui.printToDo(this.newTask);
    }
}
