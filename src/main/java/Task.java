public class Task {
    private final String name;
    private boolean status; // True = Done, False = Not Done
    Task(String name) {
        this.name = name;
        this.status = false;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + (status ? "X":" ") + "] " + this.name;
    }
}