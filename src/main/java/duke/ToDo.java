package duke;

public class ToDo extends Task{
    public ToDo(String description){
        super(description);
    }

    @Override
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "T | " + done + " | " + this.description;
    }

    public String toString() {
        return "[T]" + super.toString();
    }
}
