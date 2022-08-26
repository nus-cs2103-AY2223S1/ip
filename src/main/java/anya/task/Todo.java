package anya.task;

public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    /**
     * {@inheritDoc}
     * The type of task, T, is appended to the front of the String.
     *
     * @return T, the status icon, and the name of task in a String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * {@inheritDoc}
     * T represents a Todo.
     * 0 represents an incomplete task while 1 represents a completed task.
     *
     * @return T, an integer representing whether the task is completed, and the task name.
     */
    @Override
    public String toSave() {
        String doneVar = super.isDone ? "1" : "0";
        return "T | " + doneVar + " | " + super.name;
    }
}
