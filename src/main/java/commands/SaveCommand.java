package commands;

import duke.*;

public class SaveCommand implements Command {

    /**
     * Saves the TaskList into memory
     *
     * @param parser
     * @throws DukeException
     */
    @Override
    public void execute(Parser parser) throws DukeException {
        TaskList taskList = parser.getTaskList();

        Ui.show("Saving progress...");
        Storage.save(taskList);
        Ui.show("Successfully saved!");
    }
}
