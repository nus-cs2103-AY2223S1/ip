public class Task {
    String name;
    Boolean done = false;
    Task(String name) {
        this.name = name;
    }
    /*
     * sets a task to be done
     *
     */
    public void done() {
        this.done = true;
    }

    /*
     * sets a task to be not done
     *
     * @param input
     */
    public void unDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String mark = done ? "X" : "";
        return "[" + mark + "] " + name;
    }
}
