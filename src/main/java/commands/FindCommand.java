package commands;

import duke.DukeException;
import duke.Parser;
import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command {
    /**
     * Tries to find the item in the current TaskList with the given term
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();
        String line = parser.getLine();

        if (line.length() < 5) {
            throw new DukeException("Please enter a term to find!");
        }
        String term = line.substring(5);
        TaskList res = taskList.find(term);

        if (res.isEmpty()) {
            Ui.showLine();
            Ui.show("\tSorry! No tasks match your term. Make sure your term is the exact capitalization" +
                "as in your task list!");
            Ui.showLine();
        }

        Ui.showLine();
        Ui.show("\tHere are the matching tasks in your list:");
        res.showList();
        Ui.showLine();

    }
}
