public class ToDoTask extends Task {
    ToDoTask(String task) throws EmptyTaskException {
        super(task);
        if (super.getName().equals("")) {
            throw new EmptyTaskException();
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}