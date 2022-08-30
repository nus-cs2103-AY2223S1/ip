package Commands;

import DataStruct.Pair;
import DataStruct.TaskList;
import DaveExceptions.DaveException;
import Parser.Parser;
import Task.Deadlines;

import java.time.LocalDateTime;

public class ListCommand extends Command{

    private TaskList tasks;

    /**
     * Initialises a list command with a provided tasklist
     *
     * @param tasks Tasklist to be listed
     */
    public ListCommand(TaskList tasks, String args) {
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.toString();
    }
}
