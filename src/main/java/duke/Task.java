package duke;

public class Task {

    protected String name;
    protected boolean isDone;

    protected Task(String name, boolean isDone) throws DukeTaskException {
        if (name.equals("") || name.equals(" ")) {
            throw new DukeTaskException("the name of a task can't be empty");
        }
        this.name = name;
        this.isDone = isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String saveString() {
        return name + "|" + isDone;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
