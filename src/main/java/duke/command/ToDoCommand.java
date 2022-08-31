package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.ToDo;
import duke.Ui;

/**
 * ToDoCommand adds a ToDo to tasks.
 */
public class ToDoCommand extends Command {
    private String toDoDescription;

    /**
     * Constructor for ToDoCommand.
     * @param toDoDescription ToDo description.
     */
    public ToDoCommand(String toDoDescription) {
        super();
        this.toDoDescription = toDoDescription;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ToDo toDo = new ToDo(toDoDescription);
        tasks.addToTaskList(toDo);
        storage.save(tasks.getTasks());
        return String.format("Got it. I've added this task:\n" +
                        "added: %s\n" +
                        "Now you have %s task%s in the list.",
                toDo.toString(),
                String.valueOf(tasks.getSize()),
                tasks.getSize() == 1 ? "" : "s");
    }
}
