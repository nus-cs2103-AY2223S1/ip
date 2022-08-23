public class Todo extends Task {
    public Todo(String desc, boolean done) {
        super(desc, done);
    }

    @Override
    public String toString() { return "[T]" + super.toString(); }

    @Override
    public char getType()
    {
        return 'T';
    }

    @Override
    public String getDetail() {
        return null;
    }
}
