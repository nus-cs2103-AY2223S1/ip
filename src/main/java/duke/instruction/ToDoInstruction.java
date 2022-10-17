package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.support.DukeException;
import duke.tasks.ToDo;

/**
 * ToDoInstruction class to initiate a To Do command inputted by the user.
 *
 * @author lauralee
 */
public class ToDoInstruction implements Instruction {

    private TaskList taskList;
    private String userInput;
    private ToDo newTask;

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
    public String execute() throws DukeException.TodoException {
        try {
            this.userInput.substring(5);
        } catch (Exception StringIndexOutOfBoundsException) {
            throw new DukeException.TodoException();
        }
        String description = userInput.substring(5);
        this.newTask = new ToDo(description);
        this.taskList.addTask(this.newTask);
        return Ui.printToDo(this.newTask);
    }
}
