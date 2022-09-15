package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.support.DukeException;
import duke.tasks.Task;

/**
 * FindInstruction class to initiate a find command inputted by the user.
 *
 * @author lauralee
 */
public class FindInstruction implements Instruction {

    private TaskList taskList;
    private String userInput;
    private String findList = "";

    /**
     * Constructor for the FindInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public FindInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        String word = userInput.substring(5);
        int index = 1;
        for (int i = 1; i <= Task.getNumberTasks(); i++) {
            String taskDescription = this.taskList.getTaskArr()[i].output();
            if (taskDescription.contains(word)) {
                findList = findList + Ui.printFindTasks(index, taskDescription) + "\n";
                index++;
            }
        }
    }

    @Override
    public String execute() {
        if (findList.equals("")) {
            return DukeException.findException();
        }
        return Ui.printFind() + "\n" + findList;
    }
}
