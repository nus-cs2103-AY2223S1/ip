public class Task {

    protected String name;
    protected boolean isDone;

    public Task(String name, boolean isDone) throws DukeException {
        if (name.equals("") || name.equals(" ")) {
            throw new DukeException("the name of a task can't be empty");
        }
        this.name = name;
        this.isDone = isDone;
    }

    public void Mark() {
        isDone = true;
    }

    public void Unmark() {
        isDone = false;
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
