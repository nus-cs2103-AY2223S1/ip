package carbon.task;

import carbon.error.CarbonException;
import carbon.error.InvalidParamException;

/**
 * Enapsulates information regarding Todos.
 */
public class Todo extends Task {
    private static final int TYPEKEY = Task.getTypeKey(Task.Type.TODO);

    private Todo(String name, Boolean isDone) {
        super(name, isDone);
    }

    private static String extractName(String input) throws CarbonException {
        int len = input.length();
        int requiredLen = "todo ".length();
        if (len <= requiredLen) {
            CarbonException invalidParam = new InvalidParamException(input);
            throw invalidParam;
        } else {
            String name = input.substring("todo ".length());
            return name;
        }
    }

    /**
     * Creates a new Task object.
     *
     * @param input User text input.
     * @return Task object.
     */
    public static Task createTask(String input) {
        String name = Todo.extractName(input);
        Task todo = new Todo(name, false);
        return todo;
    }

    /**
     * Loads a task that was saved.
     *
     * @param name Name of the task.
     * @param isDone Whether the task is done or not.
     * @return Task object.
     */
    public static Task loadTask(String name, Boolean isDone) {
        Task todo = new Todo(name, isDone);
        return todo;
    }

    /** {@inheritDoc} */
    @Override
    public String encode() {
        int typeKey = Todo.TYPEKEY;
        int isDone = this.isDone ? 1 : 0;
        String result = String.format("%d|%d|%s\n", typeKey, isDone, this.name);
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        String type = "(TODO)";
        return String.format("%s %s !", type, super.toString());
    }
}
