package duke;

public class Task {
    private boolean done;
    private String description;
    private char tag;

    public Task(String description, char tag, boolean done) {
        this.description = description;
        this.tag = tag;
        this.done = done;
    }

    public Task(String description) {
        this(description,false);
    }

    private Task(String description, boolean done) {
        this(description, 'T', done);
    }

    public static Task fromSaveString(String saveString) throws RuntimeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 2) {
            throw new RuntimeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        boolean done = splitSaveString[0].endsWith("1");
        return new Task(description, done);
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.done ? 'X' : ' ', this.description);
    }

    public String toSaveData() {
        return String.format("%s,%s,\"%s\"", this.tag, this.done ? '1' : '0', this.description);
    }
}
