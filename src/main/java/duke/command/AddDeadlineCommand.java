package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.UI;
import duke.DukeException;
import duke.Task;
import duke.Deadline;
import duke.Parser;

public class AddDeadlineCommand extends Command { //Creating a duke.Deadline duke.Task and adding to the taskList
    String description;
    boolean isDone;
    String date;

    public AddDeadlineCommand(String description, boolean isDone, String date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        if (!storage.checkIsLoadingFile()) { this.date = Parser.parseDate(date); }
        Task deadline = new Deadline(this.description, this.isDone, this.date);
        taskList.addTask(deadline);
        storage.saveData(taskList);
        if (!storage.checkIsLoadingFile()) { UI.added(deadline); }
    }

}