class Task {
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

    @Override
    public String toString() {
        String out = id + ".[";
        if (done)
            out += "X";
        else
            out += " ";
        out += "] " + name;
        return out;
    }
}
