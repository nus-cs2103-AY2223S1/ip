package duke.domain;

import java.util.Objects;

import duke.domain.task.Task;

/**
 * The type Todo.
 */
public class Todo extends Task {

    private Todo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String exportString() {
        return String.format("%s%s%s", "T", super.exportString(), NULL_SYMBOL);
    }

    /**
     * Of todo.
     *
     * @param todoText
     *            the todo text
     * @return the todo
     */
    public static Todo of(String todoText) {
        assert Objects.nonNull(todoText);
        return new Todo(todoText);
    }

    /**
     * Of todo.
     *
     * @param done
     *            the done
     * @param todoText
     *            the todo text
     * @return the todo
     */
    public static Todo of(String done, String todoText) {
        assert Objects.nonNull(done);
        assert Objects.nonNull(todoText);
        Todo newTodo = Todo.of(todoText);
        if (Objects.equals(done, "1")) {
            newTodo.setComplete();
        }
        return newTodo;
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline || o instanceof Event) {
            return -1;
        }
        return super.compareTo(o);
    }
}
