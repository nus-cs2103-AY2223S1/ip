package yilia.command;

import yilia.exception.DescriptionEmptyException;
import yilia.exception.TimeFormatException;
import yilia.task.Deadline;
import yilia.task.Event;
import yilia.task.TaskList;
import yilia.task.Todo;
import yilia.task.Type;
import yilia.util.Storage;
import yilia.util.Ui;

/**
 * Represents a command to add a task into the task list.
 */
public class AddCommand extends Command {
    private final String text;
    private final Type type;
    /**
     * Class constructor specifying the content and command type.
     */
    public AddCommand(String text, Type type) {
        this.text = text;
        this.type = type;
    }
    /**
     * Executes the add command.
     *
     * @param tasks The tasks.
     * @param ui The use interface.
     * @param storage The local storage.
     * @return The message after executing.
     * @throws DescriptionEmptyException  If the description of a task is empty.
     * @throws TimeFormatException  If the given time format is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionEmptyException,
                                                                         TimeFormatException {
        if (type.equals(Type.TODO)) {
            if (text.isBlank()) {
                throw new DescriptionEmptyException(type);
            }
            Todo todo = new Todo(text);
            if (tasks.anyMatch(todo)) {
                return ui.showDuplicate(todo);
            }
            tasks.add(todo);
            return ui.showAddStatus(tasks);
        }
        String[] info = text.split("/");
        if (info.length == 1 || info[1].isBlank()) {
            throw new DescriptionEmptyException(type);
        }
        try {
            if (type.equals(Type.DEADLINE)) {
                Deadline deadline = new Deadline(info[0].strip(), info[1].strip().substring(3));
                if (tasks.anyMatch(deadline)) {
                    return ui.showDuplicate(deadline);
                }
                tasks.add(deadline);
            } else if (type.equals(Type.EVENT)) {
                Event event = new Event(info[0].strip(), info[1].strip().substring(3));
                if (tasks.anyMatch(event)) {
                    return ui.showDuplicate(event);
                }
                tasks.add(event);
            }
        } catch (Exception e) {
            throw new TimeFormatException();
        }
        return ui.showAddStatus(tasks);
    }
}
