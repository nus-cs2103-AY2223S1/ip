public class Todo extends Task {
    public Todo(String desc, boolean done) {
        super(desc, done);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public char getType() {
        return 'T';
    }

    @Override
    public String getOriginalDetail() {
        return null;
    }

    @Override
    public String getFormattedDetail() {
        return null;
    }
}
