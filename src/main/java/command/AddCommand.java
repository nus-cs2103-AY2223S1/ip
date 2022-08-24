package command;

import storage.Storage;
import task.TaskList;
import task.Deadline;
import task.Task;
import task.Event;
import task.ToDo;
import ui.Ui;

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
