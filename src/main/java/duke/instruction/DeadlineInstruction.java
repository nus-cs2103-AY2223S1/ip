package duke.instruction;

import duke.functions.TaskList;
import duke.functions.Ui;
import duke.support.DukeException;
import duke.tasks.Deadline;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * DeadlineInstruction class to initiate a Deadline command as inputted by the user.
 *
 * @author lauralee
 */
public class DeadlineInstruction implements Instruction {

    TaskList taskList;
    String userInput;
    Deadline newTask;

    /**
     * Constructor for the DeadlineInstruction class.
     *
     * @param taskList The TaskList storing the tasks added by this user.
     * @param userInput The description for the task that was just added by this user.
     */
    public DeadlineInstruction(TaskList taskList, String userInput) {
        this.taskList = taskList;
        this.userInput = userInput;
        String description = userInput.substring(9, userInput.lastIndexOf("/") - 1);
        String day = userInput.substring(userInput.lastIndexOf("/by") + 4);
        try {
            LocalDate.parse(day);
            this.newTask = new Deadline(description, day);
        } catch (DateTimeParseException exception) {
            DukeException.dateTimeException();
        } catch (DateTimeException exceptionTwo) {
            DukeException.dateTimeException();
        }
    }

    @Override
    public String execute() {
        this.taskList.addTask(this.newTask);
        return Ui.printDeadline(this.newTask);
    }
}
