package duke.command;

import duke.*;

import java.time.LocalDate;
import java.util.Objects;

public class AddCommand extends Command {
    String description = "";
    LocalDate date = null;
    String type = "";

    public AddCommand(String type, String name , LocalDate date) {
        this.type = type;
        this.description = name;
        this.date = date;
    }

    public AddCommand(String type, String name ) {
        this.type = type;
        this.description = name;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task task = null;
        if(description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        switch(type) {
            case "todo" :
                task = new Todo(description);
            case "event":
                task = new Event(description,date);
            case "deadline" :
                task = new Deadline(description,date);
        }

        taskList.addTask(task);
        ui.showAddCommand(task, taskList.size());
        storage.writeFile(taskList.getTaskList());
    }
}
