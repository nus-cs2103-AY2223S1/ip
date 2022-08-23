package duke.modules.todos;

import duke.MessagefulException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static duke.Ui.sayError;

// "We will add validation to this later" - No one

public class TaskList implements Iterable<Task> {
    private final List<Task> todos;

    public TaskList(Storage storage) {
        List<Task> todos;
        try {
            todos = storage.loadList();
        } catch (MessagefulException e) {
            sayError(e);
            todos = new ArrayList<>();
        }
        this.todos = todos;
    }

    public Task get(int index) {
        return todos.get(index);
    }

    public int size() {
        return todos.size();
    }

    public boolean add(Task task) {
        return todos.add(task);
    }

    public Task remove(int index) {
        return todos.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return todos.iterator();
    }
}
