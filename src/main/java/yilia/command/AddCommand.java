package yilia.command;

import yilia.Storage;
import yilia.Type;
import yilia.Ui;
import yilia.exception.DescriptionEmptyException;
import yilia.exception.TimeFormatException;
import yilia.task.*;

public class AddCommand extends Command {
    private final String text;
    private final Type type;
    public AddCommand(String text, Type type) {
        this.text = text;
        this.type = type;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DescriptionEmptyException, TimeFormatException {
        if (type.equals(Type.TODO)) {
            if (text.isBlank()) {
                throw new DescriptionEmptyException(type);
            }
            Todo todo = new Todo(text);
            tasks.add(todo);
            ui.showAddStatus(tasks);
            return;
        }
        String info[] = text.split("/");
        if (info.length == 1 || info[1].isBlank()) {
            throw new DescriptionEmptyException(type);
        }
        try {
            if (type.equals(Type.DEADLINE)) {
                Deadline deadline = new Deadline(info[0].strip(), info[1].strip().substring(3));
                tasks.add(deadline);
                ui.showAddStatus(tasks);
            } else if (type.equals(Type.EVENT)) {
                Event event = new Event(info[0].strip(), info[1].strip().substring(3));
                tasks.add(event);
                ui.showAddStatus(tasks);
            }
        } catch (Exception e) {
            throw new TimeFormatException();
        }
    }
}