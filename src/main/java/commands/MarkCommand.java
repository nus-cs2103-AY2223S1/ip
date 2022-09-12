package commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;

public class MarkCommand implements Command {

    /**
     * Marks the item at index in the TaskList as done
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();
        if (taskList.isEmpty()) {
            throw new DukeException("OOPS!!! Cannot mark when list is empty");
        }
        if (line.length() <= 5) {
            throw new DukeException("OOPS!!! Please enter a number after mark");
        }
        int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
        taskList.get(index - 1).markAsDone();
        Ui.show("\tNice! I've marked this task as done:");
        Ui.show("\t" + taskList.get(index - 1));
    }
}
