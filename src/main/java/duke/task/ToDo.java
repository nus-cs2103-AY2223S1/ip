package duke.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public static ToDo parseFile(String string) {
        String[] details = string.split(" \\| ");
        ToDo todo = new ToDo(details[2]);
        if (details[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toDataFormat() {
        String completed = "0";
        if (this.getStatusIcon().equals("X")) {
            completed = "1";
        }
        return "T | " + completed + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
