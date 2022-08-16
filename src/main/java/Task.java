abstract class Task {
    private final int id;
    private final String name;
    private boolean done;

    Task(int id, String name) {
        this.id = id;
        this.name = name;
        this.done = false;
    }

    void setDone() {
        this.done = true;
    }

    void setNotDone() {
        this.done = false;
    }

    int getId() {
        return id;
    }

    boolean getStatus() {
        return done;
    }

    @Override
    public String toString() {
        return name;
    }
}
