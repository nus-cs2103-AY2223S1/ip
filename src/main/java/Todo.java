public class Todo extends Task{

    private String deadline;

    public Todo(String name, boolean done) {
        super(name, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}