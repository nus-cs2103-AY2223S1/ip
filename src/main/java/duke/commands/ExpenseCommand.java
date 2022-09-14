package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.tasks.ExpenseTask;

public class ExpenseCommand implements Command {

    private String expenseName;
    private int expenseAmount;
    /**
     * Default constructor of the expense command.
     *
     * @param expenseName Name of the expense.
     * @param expenseAmount Amount of the expense.
     */
    public ExpenseCommand(String expenseName, int expenseAmount) {
        this.expenseName = expenseName;
        this.expenseAmount = expenseAmount;
    }

    @Override
    public boolean isBye() {
        return false;
    }

    /**
     * Runs the expense command by adding the expense into the tasklist and storage.
     *
     * @param tasks TaskList that contains the temporary tasks.
     * @param storage Storage that the tasks are saved at.
     * @return String output of executing the task
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        ExpenseTask newTask = new ExpenseTask(expenseName, expenseAmount);
        tasks.add(newTask);
        storage.writeAll(tasks);
        String output = "";
        output += "Got it. I've added this task:\n";
        output += newTask + "\n";
        output += "Now you have " + tasks.getSize() + " tasks in the list\n";
        return output;
    }


}
