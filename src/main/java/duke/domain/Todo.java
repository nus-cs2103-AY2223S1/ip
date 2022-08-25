package duke.domain;

import java.util.Objects;

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
        Todo newTodo = Todo.of(todoText);
        if (Objects.equals(done, "1")) {
            newTodo.setComplete();
        }
        return newTodo;
    }
}
