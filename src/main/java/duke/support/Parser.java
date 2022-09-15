package duke.support;

import duke.functions.TaskList;
import duke.gui.Main;
import duke.instruction.ByeInstruction;
import duke.instruction.DeadlineInstruction;
import duke.instruction.DeleteInstruction;
import duke.instruction.EventInstruction;
import duke.instruction.ExceptionInstruction;
import duke.instruction.FindInstruction;
import duke.instruction.Instruction;
import duke.instruction.ListInstruction;
import duke.instruction.MarkInstruction;
import duke.instruction.SnoozeInstruction;
import duke.instruction.ToDoInstruction;
import duke.instruction.UnMarkInstruction;

/**
 * Parser class to make sense of user input.
 *
 * @author lauralee
 */
public class Parser {

    private TaskList taskList;

    /**
     * Constructor for the Parser class.
     */
    public Parser() {
        this.taskList = Main.getDuke().getUserTaskList();
    }

    /**
     * Initiates scanner system which receives input from users.
     *
     * @param input The input given by the user.
     * @return The instruction that should be initiated based on the user's input.
     */
    public Instruction userInput(String input) {

        String a = input;
        Instruction instruction = null;

        if (a.equals("list")) {
            instruction = new ListInstruction(this.taskList);
        } else if (a.contains("unmark")) {
            instruction = new UnMarkInstruction(this.taskList, input);
        } else if (a.contains("mark")) {
            instruction = new MarkInstruction(this.taskList, input);
        } else if (a.contains("delete")) {
            instruction = new DeleteInstruction(this.taskList, input);
        } else if (a.contains("todo")) {
            instruction = new ToDoInstruction(this.taskList, input);
        } else if (a.contains("deadline")) {
            instruction = new DeadlineInstruction(this.taskList, input);
        } else if (a.contains("event")) {
            instruction = new EventInstruction(this.taskList, input);
        } else if (a.contains("find")) {
            instruction = new FindInstruction(this.taskList, input);
        } else if (a.equals("bye")) {
            instruction = new ByeInstruction();
        } else if (a.contains("snooze")) {
            instruction = new SnoozeInstruction(this.taskList, input);
        } else {
            instruction = new ExceptionInstruction();
        }
        return instruction;
    }

    /**
     * Retrieves TaskList created by the user.
     *
     * @return The TaskList created by the user.
     */
    public TaskList getTaskList() {
        return this.taskList;
    }

}
