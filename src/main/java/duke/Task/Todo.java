package duke.Task;

public class Todo extends Task {
    public Todo(String description, Boolean isDone) {
        super(description, isDone);

    }
    public Todo(String description) {
        super(description);
    }
    @Override
    public String formatTaskString() {
        return String.format("T|%s|%s", this.isDone, this.description);
    }

    @Override
    public String toString(){
        return String.format("[T] [%s] %s", this.getStatusIcon(), this.description);
    }

}
