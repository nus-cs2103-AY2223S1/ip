public class Todo extends Task {

    public Todo(ParsedInput parsedInput) {
        super(parsedInput);
    }

    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    @Override
    public String toString() {
        String result = this.getTypeIcon() + this.getStatusIcon() + this.taskName;
        return result;
    }
}
