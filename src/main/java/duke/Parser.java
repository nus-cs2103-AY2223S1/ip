package duke;

/**
 * deals with making sense of user commands in Duke
 */
public class Parser {
    private static Ui ui = new Ui();

    /**
     * Interprets the user command and executes followup actions.
     *
     * @param command input passed by user.
     * @param tasks the TaskList for current Duke instance to be modified.
     * @throws DukeException wrong input.
     */
    public static String parse(String command, TaskList tasks) throws DukeException {
        // user wants to display list
        if (command.equals("list")) {
            return tasks.toString();

        // user wants to mark certain task as done
        } else if (command.startsWith("mark")) {
            String str = command.replace("mark ", "");
            int index = Integer.valueOf(str);
            tasks.mark(index);
            return "Nice! I've marked this task as done:\n" + tasks.getString(index);

        // user wants to unmark certain tasks to not done
        } else if (command.startsWith("unmark")) {
            String str = command.replace("unmark ", "");
            int index = Integer.valueOf(str);
            tasks.unmark(index);
            return "Okay, I've marked this task as undone:\n" + tasks.getString(index);

        // user wants to add new todo task
        } else if (command.startsWith("todo")) {
            String str = command.replace("todo", "");
            String message = tasks.add(str, Duke.Type.TODO);
            return message;

        // user wants to add new deadline task
        } else if (command.startsWith("deadline")) {
            String message = tasks.add(command, Duke.Type.DEADLINE);
            return message;

        // user wants to add new event task
        } else if (command.startsWith("event")) {
            String message = tasks.add(command, Duke.Type.EVENT);
            return message;

        // user wants to delete a certain task
        } else if (command.startsWith("delete")) {
            String message = tasks.delete(command);
            return message;

        // user wants to find tasks with a keyword
        } else if (command.startsWith("find")) {
            String keyword = command.replace("find ", "");
            String message = tasks.find(keyword);
            return message;

        // user wants to exit
        } else if (command.equals("bye")) {
            return "Your tasks have been saved.\nBye. Hope to see you again soon!";

        // unknown command
        } else {
            throw new UnknownInputException();
        }
    }
}
