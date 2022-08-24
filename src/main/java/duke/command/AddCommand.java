package duke.command;

import duke.*;
import duke.exception.DukeException;
import duke.task.*;

import java.time.LocalDate;

public class AddCommand extends Command {

    private String taskType;
    private String description;
    private LocalDate date;

    public AddCommand(String taskType, String description, LocalDate date) {
        this.taskType = taskType;
        this.description = description;
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task task = null;
        switch (taskType) {
            case "T":
                task = new ToDo(description);
                break;
            case "E":
                task = new Event(description, date);
                break;
            case "D":
                task = new Deadline(description, date);
                break;
        }
        tasks.add(task);
        storage.saveData(tasks);
        ui.printMessage("Got it. I've added this task:\n" + task + "\nNow you have " + tasks.size() + " tasks.");
    }

    @Override
    public String getCommand() {
        return "add";
    }

    @Override
    public String toString() {
        return "add " + taskType + " " + description + " " + date;
    }
}
