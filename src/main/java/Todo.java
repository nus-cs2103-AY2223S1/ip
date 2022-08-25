public class Todo extends Task {
    public Todo(String description, boolean done) throws DukeException {
           super(description, done);
           if (description.equals("")) {
               throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
           }
    }

    @Override
    public boolean isTodo() {
        return true;
    };
    @Override
    public boolean isDeadline() {
        return false;
    };
    @Override
    public boolean isEvent() {
        return false;
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
