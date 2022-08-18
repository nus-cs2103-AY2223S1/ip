public class Todo extends Task {

    Todo(String descrition) {
        super(descrition);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
