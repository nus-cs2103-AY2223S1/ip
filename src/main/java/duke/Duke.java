package duke;

import duke.task.TaskList;
import duke.exception.InvalidInputException;
import duke.expenses.ExpenseList;

/**
 * Duke is a task list manager for tasks, deadlines and events!
 * 
 * @author Kiyan Ang Ping Young (@kynapy)
 * @version v0.1
 * @since 2022-09-10
 */

public class Duke {
    private TaskList tasks;
    private ExpenseList expenses;
    private Storage storage;
    private Parser parser;

    /**
     * Constructor for Duke.
     * @param filepath This is the filepath where the data file duke.txt would be stored.
     */

    public Duke(String filepath) {
        this.storage = new Storage(filepath);
        try {
            storage.load();
            tasks = new TaskList(storage.loadTasks());
            expenses = new ExpenseList(storage.loadExpenses());
        } catch (Exception e) {
            this.tasks = new TaskList();
        }
        this.parser = new Parser(tasks, expenses);
    }

    public String getResponse(String input) {
        try {
            String reply = parser.parse(input);
            storage.store(tasks.getList(), expenses.getList());
            return "Greg says: " + reply;
        } catch (InvalidInputException e) {
            return "Greg says: I'm sorry, I don't know what that means :(";
        }
    }
}
