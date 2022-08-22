import java.util.*;
import java.util.stream.Stream;

public class TaskList implements Savable<TaskList> {
    private ArrayList<Task> tasks = new ArrayList<>();

    public boolean add(Task task) {
        return tasks.add(task);
    }

    public Task get(int i) throws DukeException.TaskNotFoundException {
        throwIfNotValidIndex(i);
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task remove(int i) throws DukeException.TaskNotFoundException {
        throwIfNotValidIndex(i);
        return this.tasks.remove(i);
    }

    private boolean isValidIndex(int i) {
        return !(i < 0 || i >= this.size());
    }

    private void throwIfNotValidIndex(int i) throws DukeException.TaskNotFoundException{
        if (!isValidIndex(i)) {
            throw new DukeException.TaskNotFoundException(i + 1);
        }
    }

    @Override
    public String toString() {
        String message = Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> x + 1 + ". " + tasks.get(x).toString())
                .reduce("", (x, y) -> x + y + "\n");
        return message;
    }

    @Override
    public String toFormattedString() {
        String s;
        s = Stream.iterate(0, x -> x + 1)
                .limit(tasks.size())
                .map(x -> this.tasks.get(x).toFormattedString())
                .reduce("", (x, y) -> x + y + "\n");
        return s;
    }

    @Override
    public TaskList read(String formattedString) throws DukeException.ReadAttributeException {
        TaskList result = new TaskList();
        Arrays.stream(formattedString.split(System.lineSeparator()))
                .filter(s -> !s.trim().equals(""))
                .map(s -> Task.parseTask(s))
                .forEach(task -> result.add(task));
        return result;
    }
}
