package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toStorageString() {
        return "T" + super.toStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Todo) {
            Todo temp = (Todo) obj;
            return temp.description.equals(this.description) && temp.isDone == this.isDone;
        }
        return false;
    }
}
