public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toFileRepresentation() {
        return "T | " + super.toFileRepresentation();
    }

    public static Todo fromFileRepresentation(String rep) {
        String[] args = rep.split(" \\| ");
        boolean isDone = args[1].equals("1");
        String description = args[2];
        Todo result = new Todo(description);
        if (isDone) {
            result.markDone();
        }
        return result;
    }

}
