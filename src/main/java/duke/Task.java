package duke;

public class Task {
    private boolean isDone;
    private String description;
    private char tag;

    public Task(String description, char tag, boolean isDone) {
        this.description = description;
        this.tag = tag;
        this.isDone = isDone;
    }

    public Task(String description) {
        this(description,false);
    }

    private Task(String description, boolean isDone) {
        this(description, 'T', isDone);
    }

    public static Task fromSaveString(String saveString) throws DukeException {
        String[] splitSaveString = saveString.split("(\",\")|(\",)|(,\")|\"");
        if(splitSaveString.length != 2) {
            throw new DukeException("Tried to read unexpected save data.");
        }
        String description = splitSaveString[1];
        boolean isDone = splitSaveString[0].endsWith("1");
        return new Task(description, isDone);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.tag, this.isDone ? 'X' : ' ', this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o instanceof Task == false) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone &&
                description.equals(task.description) &&
                tag == task.tag;
    }

    public String toSaveData() {
        return String.format("%s,%s,\"%s\"", this.tag, this.isDone ? '1' : '0', this.description);
    }
}
