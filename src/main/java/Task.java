public class Task {
    protected boolean completed;
    protected String name;
    protected int count;

    public Task(String name, int count) throws MissingDescriptionException {
        if (name.equals("") || name.equals(" ")) {
            throw new MissingDescriptionException();
        } else {
            this.name = name;
            this.count = count;
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
        return String.format("%d." + comp + name, count);
    }

    public String toStr() {
        String comp = this.completed
                ? "[X]"
                : "[ ]";
        return comp + name;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
