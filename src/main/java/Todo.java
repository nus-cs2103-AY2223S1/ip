public class Todo extends Task {
    protected boolean Todo = true;

    public Todo(String description) throws DukeException {
           super(description);
           if (description.equals("")) {
               throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
           }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
