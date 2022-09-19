package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDate;

public class DeadlineCommand extends Command{

    private String description;
    private LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        assert storage != null;
        assert taskList != null;
        assert ui != null;

        String output = "";
        Task task = new Deadline(description, by);
        taskList.add(task);
        output += ui.printAddTask(task.toString());
//        output += taskList.toString();
        storage.saveFileData(taskList);
        return output;
    }
}
