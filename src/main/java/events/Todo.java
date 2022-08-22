package events;

public class Todo extends Task {
    public Todo(String text) {
        super(text);
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }

    @Override
    public String exportString() {
        return String.format("%s%s%s",
                "T",
                super.exportString(),
                "$_$");
    }
}
