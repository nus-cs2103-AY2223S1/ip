package duke.command;

import duke.task.Todo;

/**
 * A command that adds a Todo to the task list.
 */
public class AddTodoCommand extends AddCommand {
    private AddTodoCommand(String command, Todo todo) {
        super(command, todo);
        assert(command.startsWith("todo"));
    }

    /**
     * Factory method taking in input string from user.
     * Throws IllegalArgumentException if the todo's description is not given.
     *
     * @param command input string from user, prefixed with "todo".
     * @return AddTodoCommand instance that adds a todo to the task list when executed.
     * @throws IllegalArgumentException if input string from user is invalid.
     */
    public static AddTodoCommand of(String command) throws IllegalArgumentException {
        assert(command.startsWith("todo"));

        String tag;
        String commandWithoutTag;
        String[] commandTagArr = command.split("#");
        if (commandTagArr.length == 2) {
            tag = commandTagArr[1];
            commandWithoutTag = commandTagArr[0];
        } else if (commandTagArr.length > 2) {
            throw new IllegalArgumentException(":( OOPS!!! Only 1 tag can be provided.\n");
        } else {
            tag = "";
            commandWithoutTag = command;
        }

        boolean isDone = commandWithoutTag.contains("/done");
        if (isDone) {
            commandWithoutTag = commandWithoutTag.replace("/done", "");
        }

        String text = commandWithoutTag.replaceFirst("todo", "").strip();
        if (text.isEmpty()) {
            throw new IllegalArgumentException(":( OOPS!!! The description of a todo cannot be empty.\n");
        }

        return new AddTodoCommand(command, new Todo(isDone, text, tag));
    }
}
