package duke.command;

import duke.*;

public class AddEventCommand extends Command { //Creating an duke.Event duke.Task and adding to the taskList
    String description;
    boolean isDone;
    String date;

    public AddEventCommand(String description, boolean isDone, String date) {
        this.description = description;
        this.isDone = isDone;
        this.date = date;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws DukeException {
        if (!storage.checkIsLoadingFile()) { this.date = Parser.parseDate(date); }
        Task event = new Event(this.description, this.isDone, this.date);
        taskList.addTask(event);
        storage.saveData(taskList);
        if (!storage.checkIsLoadingFile()) { UI.added(event); }
    }

}