package duke.helper;

import duke.task.TaskList;

/**
 * Encapsulates a parser that parses inputs
 */
public class Parser {

    /**
     * Parses any of command given and call the respective functions
     *
     * @param in the input command line given
     * @param list the taskList being used
     */
    public static String parse(String in, TaskList list, String filePath) {
        String message;

        if (in.equals("bye")) {
            return Ui.bye();
        } else if (in.equals("help")) {
            message = Command.help();

        } else if (in.startsWith("find")) {
            message = Command.find(in, list);

        } else if (in.equals("clear")) {
            message = Command.clear(list);

        } else if (in.equals("list")) {
            message = Command.list(list);

        } else if (in.startsWith("mark")) {
            message = Command.mark(in, list);

        } else if (in.startsWith("unmark")) {
            message = Command.unmark(in, list);

        } else if (in.startsWith("delete")) {
            message = Command.delete(in, list);

        } else if (in.startsWith("todo")
                || in.startsWith("deadline")
                        || in.startsWith("event")) {
            message = Command.createTask(in, list);
        } else {
            message = Ui.invalidCommand();
        }

        FileWriting.update(filePath, list);
        return message + "\n";
    }
}
