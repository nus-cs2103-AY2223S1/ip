public abstract class Task {
    private String name;
    private boolean completed;
    private char type;

    public Task (String name, char type, boolean completed) {
        this.name = name;
        this.type = type;
        this.completed = completed;
    }

    public Task (String name, char type) {
        this(name, type,false);
    }

    public void markComplete() {
        this.completed = true;
    }

    public void markIncomplete() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s", type, completed ? "X" : " ", name);
    }
}
