package bloop;

public class ToDo extends Task {

    public ToDo(String task) {
        super(task);
    }

    protected String getBy() {
        return "";
    }

    protected char getType() {
        return 'T';
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
