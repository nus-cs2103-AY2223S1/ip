package duke;

/**
 * deals with making sense of user commands in Duke
 */
public class Parser {
    private static Ui ui = new Ui();

    /**
     * Interprets the user command and executes followup actions
     * @param command input passed by user
     * @param tasks the TaskList for current Duke instance to be modified
     * @throws DukeException wrong input
     */
    public static void parse(String command, TaskList tasks) throws DukeException {
        if (command.equals("list")) {
            ui.printMessage(tasks.toString());
        } else if (command.startsWith("mark")) {
            String str = command.replace("mark ", "");
            int index = Integer.valueOf(str);
            tasks.mark(index);
            ui.printMessage("Nice! I've marked this task as done:\n" + tasks.getString(index));
        } else if (command.startsWith("unmark")) {
            String str = command.replace("unmark ", "");
            int index = Integer.valueOf(str);
            tasks.unmark(index);
            ui.printMessage("Nice! I've marked this task as done:\n" + tasks.getString(index));
        } else if (command.startsWith("todo")) {
            String str = command.replace("todo", "");
            tasks.add(str, Duke.Type.TODO);
        } else if (command.startsWith("deadline")) {
            tasks.add(command, Duke.Type.DEADLINE);
        } else if (command.startsWith("event")) {
            tasks.add(command, Duke.Type.EVENT);
        } else if (command.startsWith("delete")) {
            tasks.delete(command);
        } else {
            throw new UnknownInputException();
        }
    }
}
