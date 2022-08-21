public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public  Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        String s = super.toString();
        return "[T]" + s;
    }


    public String toFile() {
        String s = super.toFile();
        return "T," + s;
    }
}
