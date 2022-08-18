public class Todo extends Task {

    public Todo(String name, boolean isDone) throws DukeException {
        super(name, isDone);
    }

    @Override
    public String toString() {
        String temp;
        if (super.isDone) {
            temp = "[X] " + super.name;
        } else {
            temp = "[ ] " + name;
        }
        return "[T]" + temp;
    }


}
