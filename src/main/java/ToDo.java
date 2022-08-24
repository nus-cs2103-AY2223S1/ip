public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo parseFile(String data) {
        String[] details = data.split(" \\| ");
        ToDo todo = new ToDo(details[2]);
        if (details[1].equals("1")) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toDataFormat() {
        String completed = "";
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        return "T | " +  completed + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}