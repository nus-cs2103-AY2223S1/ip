package duke.commands;

import duke.exceptions.EmptyTaskDescException;
import duke.exceptions.EmptyTaskDateException;
import duke.exceptions.NoSuchTaskTypeException;
import duke.exceptions.UnrecognisedDateException;
import duke.tasks.*;
import duke.ui.Ui;
import duke.utils.Storage;
import duke.utils.TaskParser;

public class AddTaskCommand extends Command {

    private Storage storage;

    private Ui ui;

    private TaskList tasks;

    private TaskType type;

    private String taskString;

    public AddTaskCommand(Storage storage, Ui ui, TaskList tasks, TaskType type, String taskString) {
        this.storage = storage;
        this.ui = ui;
        this.tasks = tasks;
        this.type = type;
        this.taskString = taskString;
    }

    @Override
    public String execute() {
        try {
            Task newTask = TaskParser.stringToTask(type, taskString);
            tasks.addTask(newTask);
            storage.saveToFile(tasks.getList());

            String response = String.format(
                    "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                    newTask, tasks.getSize());
            return response;
        } catch (EmptyTaskDescException | EmptyTaskDateException | NoSuchTaskTypeException |
                 UnrecognisedDateException e) {

            String response = String.format("Oops! %s", e.getMessage());
            return response;
        }
    }
}
