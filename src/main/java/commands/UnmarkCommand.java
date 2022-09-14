package commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand implements Command {

    /**
     * Marks the item at index in the TaskList as undone
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();
        if (taskList.isEmpty()) {
            throw new DukeException("Oops! Cannot unmark when list is empty");
        }
        if (line.length() <= 7) {
            throw new DukeException("Oops! Please enter a number after unmark");
        }
        int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
        taskList.get(index - 1).markAsUndone();
        Ui.show("\tNice! I've marked this task as not done yet:");
        Ui.show("\t" + taskList.get(index - 1));
    }
}
