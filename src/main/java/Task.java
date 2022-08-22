public class Task {
    protected boolean completed;
    protected String name;

    public Task(String name) throws MissingDescriptionException {
        if (name.equals("") || name.equals(" ")) {
            throw new MissingDescriptionException();
        } else {
            this.name = name;
            this.completed = false;
        }

    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    @Override
    public String toString() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return comp + name;
    }

    public String toData() {
        return this.toString();
    }
}
