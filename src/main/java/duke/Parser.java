package duke;

/**
 * deals with making sense of user commands in Duke.
 */
public class Parser {
    /**
     * Interprets the user command and executes followup actions.
     *
     * @param command input passed by user.
     * @param tasks the TaskList for current Duke instance to be modified.
     * @throws DukeException wrong input.
     */
    public static String parse(String command, TaskList tasks, Storage storage) throws DukeException {
        if (command.equals("list")) {
            return tasks.toString();
        } else if (command.startsWith("mark")) {
            int index = getIndex(command);
            return tasks.mark(index);

        } else if (command.startsWith("unmark")) {
            int index = getIndex(command);
            return tasks.unmark(index);

        } else if (command.startsWith("todo")) {
            return tasks.addTodo(command);

        } else if (command.startsWith("deadline")) {
            return tasks.addDeadline(command);

        } else if (command.startsWith("event")) {
            return tasks.addEvent(command);

        } else if (command.startsWith("delete")) {
            return tasks.delete(command);

        } else if (command.startsWith("find")) {
            return tasks.find(command);

        } else if (command.equals("bye")) {
            return storage.save(tasks);

        } else if (command.startsWith("priority")) {
            return tasks.setPriority(command);

        } else {
            throw new UnknownInputException();
        }
    }

    /**
     * Returns the index of task to mark or unmark.
     *
     * @param command input by user.
     * @return index of task in list.
     */
    private static int getIndex(String command) {
        String index = command.split(" ", 2)[1];
        return Integer.parseInt(index);
    }
}
