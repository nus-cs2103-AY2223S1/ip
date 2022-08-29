package duke.task;

public abstract class Task {
    private final String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    /**
     * Checks if <code>Task</code> name contains keyword.
     *
     * @param keyword the keyword to check.
     * @return true if <code>keyword</code> is found in name.
     */
    public boolean isNameContaining(String keyword) {
        return name.contains(keyword);
    }

    public static Task fromString(String inputString) {
        char typeOfTask = inputString.charAt(1);
        switch (typeOfTask) {
        case 'T':
            return Todo.fromString(inputString);
        case 'D':
            return Deadline.fromString(inputString);
        case 'E':
            return Event.fromString(inputString);
        }
        return null;
    }

    @Override
    public String toString() {
        String icon = this.isDone ? "[X] " : "[ ] ";
        return icon + this.name;
    }
}
