package commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand implements Command {

    /**
     * Deconstructs the string to delete the appropriate item in the TaskList
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();

        if (line.length() <= 7) {
            throw new DukeException("OOPS!!! Please enter a number after delete");
        }
        int index = Integer.parseInt(line.replaceAll("[^0-9]", ""));
        if (index > taskList.size() || index < 0) {
            throw new DukeException("OOPS!!! Invalid number to delete");
        }
        Ui.showLine();
        Ui.show("\tNoted. I've removed this task:");
        Ui.show("\t\t" + taskList.get(index - 1));
        taskList.remove(index - 1);
        Ui.show("\tNow you have " + taskList.size() + " tasks in the list.");
        Ui.showLine();
    }
}
