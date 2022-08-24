package seedu.duke.command;

import seedu.duke.storage.Storage;
import seedu.duke.task.TaskList;
import seedu.duke.task.Deadline;
import seedu.duke.task.Task;
import seedu.duke.task.Event;
import seedu.duke.task.ToDo;
import seedu.duke.ui.Ui;

public class AddCommand extends Command {
    String typeOfTask;
    String description;
    String deadline;

    public AddCommand(String typeOfTask, String description) {
        super(false);
        this.typeOfTask = typeOfTask;
        this.description = description;
    }

    public AddCommand(String typeOfTask, String description, String deadline) {
        super(false);
        this.typeOfTask = typeOfTask;
        this.description = description;
        this.deadline = deadline;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = new Task("null");
        if (typeOfTask.equals("deadline")) {
            task = new Deadline(description, deadline);
        } else if (typeOfTask.equals("event")) {
            task = new Event(description, deadline);
        } else if (typeOfTask.equals("todo")) {
            task = new ToDo(description);
        }

        tasks.addTask(task);
        ui.addTask(task);
        ui.displayNumberOfTasks(tasks.getNumberOfTasks());
        storage.writeToFile(tasks);
    }

}
