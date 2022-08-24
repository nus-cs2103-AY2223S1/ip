public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }
    
    public static ToDo taskFromSave(String saveString) {
        String[] tokens = saveString.split(" \\| ");
        ToDo todo = new ToDo(tokens[2]);
        if (tokens[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }

    @Override
    public String saveString() {
        return "T | " + super.saveString();
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
