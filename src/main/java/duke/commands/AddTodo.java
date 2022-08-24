package duke.commands;

import duke.entities.*;
import duke.enums.*;
import duke.exceptions.*;
import duke.lists.*;

public class AddTodo extends ShowList {
    protected String descrition;
    protected String instruction;

    public AddTodo(TaskList list, String description, String input) {
        super(list);
        this.descrition = description;
        this.instruction = input;
    }

    /**
     * Add new todo to the task list
     * 
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {
        Todo current_todo = new Todo(descrition);
        tasks.addTask(current_todo);
        wrapWithLines(Messages.ADD_TODO.toString(), current_todo.toString());
    }

    public String getInput() {
        return instruction;
    }

}
