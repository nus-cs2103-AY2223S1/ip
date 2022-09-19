package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Event;
import duke.task.Task;

import java.time.LocalDate;

public class EventCommand extends Command{

    private String description;
    private LocalDate at;

    public EventCommand(String description, LocalDate at) {

        this.description = description;
        this.at = at;
    }

    @Override
    public String execute(Storage storage, TaskList taskList, Ui ui) throws DukeException {
        String output = "";
        Task task = new Event(description, at);
        taskList.add(task);
        output += ui.printAddTask(task.toString());
//        output += taskList.toString();
        storage.saveFileData(taskList);
        return output;
    }
}
