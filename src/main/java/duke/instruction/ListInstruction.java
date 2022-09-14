package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;

/**
 * ListInstruction class to initiate a List command inputted by the user.
 *
 * @author lauralee
 */
public class ListInstruction implements Instruction {

    TaskList taskList;

    /**
     * Constructor for the ListInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     */
    public ListInstruction(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public String execute() {
        return Ui.printList(this.taskList);
    }

}
