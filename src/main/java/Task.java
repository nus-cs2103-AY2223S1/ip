public class Task {
    private String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    void Done() {
        this.done = true;
    }

    void unDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String out = "";
        if (this.done) {
            out += "[X]";
        } else {
            out += "[ ]";
        }
        out += " " + this.name;
        return out;
    }
}
