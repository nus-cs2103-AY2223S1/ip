public class Task {
    String name;
    Boolean done = false;
    Task(String name) {
        this.name = name;
    }
    public void done() {
        this.done = true;
    }

    public void unDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String mark = done ? "X" : "";
        return "[" + mark + "] " + name;
    }
}
