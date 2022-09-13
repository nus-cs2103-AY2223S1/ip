package duke.commands;

import java.time.LocalDate;
import java.util.List;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * This class represents a summary command
 * Executing this command gives users a summary of their
 * activities so far
 */
public class SummariseCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String execute(Ui ui, Storage storage, TaskList taskList) throws DukeException {
        LocalDate currentDate = LocalDate.now();
        LocalDate sevenDaysAgo = currentDate.minusDays(7);
        List<Task>[] results;
        results = (List<Task>[]) taskList.summary(sevenDaysAgo, currentDate);
        return ui.printSummary(results[0], results[1], results[2]);
    }
}
