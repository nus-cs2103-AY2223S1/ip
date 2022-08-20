public class Todo extends Task {
    public Todo(String s) {
        super(s);
    }
    @Override
    public String toPrint() {
        return "T" + super.toPrint();
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
