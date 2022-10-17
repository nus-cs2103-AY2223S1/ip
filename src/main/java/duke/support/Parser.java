package duke.support;

import duke.functions.TaskList;
import duke.functions.storage.Load;
import duke.functions.storage.LoadDeadline;
import duke.functions.storage.LoadEvent;
import duke.functions.storage.LoadToDo;
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
import duke.tasks.Task;

/**
 * Parser class to make sense of input.
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
     * Overloaded constructor for the Parser class.
     *
     * @param taskList The taskList for this instance of Duke.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
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
        } else if (a.startsWith("unmark")) {
            instruction = new UnMarkInstruction(this.taskList, input);
        } else if (a.startsWith("mark")) {
            instruction = new MarkInstruction(this.taskList, input);
        } else if (a.startsWith("delete")) {
            instruction = new DeleteInstruction(this.taskList, input);
        } else if (a.startsWith("todo")) {
            instruction = new ToDoInstruction(this.taskList, input);
        } else if (a.startsWith("deadline")) {
            instruction = new DeadlineInstruction(this.taskList, input);
        } else if (a.startsWith("event")) {
            instruction = new EventInstruction(this.taskList, input);
        } else if (a.startsWith("find")) {
            instruction = new FindInstruction(this.taskList, input);
        } else if (a.startsWith("bye")) {
            instruction = new ByeInstruction();
        } else if (a.startsWith("snooze")) {
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

    /**
     * Loads tasks from Task List previously stored in the given filepath.
     *
     * @param task The description of the task from the stored Task List to be added
     *             into the new Task List.
     * @return The task from the stored Task List to be added into the new Task List.
     */
    public Task loadFromStorage(String task) {
        Task taskBeingAdded;
        Load taskToBeAdded = null;
        if (task.startsWith("[T]")) {
            taskToBeAdded = new LoadToDo(task);
        } else if (task.startsWith("[E]")) {
            taskToBeAdded = new LoadEvent(task);
        } else {
            taskToBeAdded = new LoadDeadline(task);
        }

        taskBeingAdded = taskToBeAdded.loadTask();

        return taskBeingAdded;
    }

}
