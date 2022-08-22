package jduke.commands;

import jduke.task.ToDo;

public class TodoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public static final String FORMAT = "todo <description>";

    private final ToDo todo;
    public TodoCommand(String params) {
        this.todo = new ToDo(params);
    }

    @Override
    public String execute() {
        this.tasklist.addTodo(todo);
        return String.format("|  added task:%n|    %s%n", this.todo);
    }
}
