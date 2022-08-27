package duke;

<<<<<<< HEAD
public class ToDo extends Task{
    /**
     * Constructs an instance of Deadline
     *
     * @param description Description String
     */
    public ToDo(String description){
=======
public class ToDo extends Task {
    public ToDo(String description) {
>>>>>>> branch-A-CodingStandard
        super(description);
    }
    /** @inheritdoc */
    @Override
    public String changeFormat() {
        String done = isDone ? "1" : "0";
        return "T | " + done + " | " + this.description;
    }

    /** @inheritdoc */
    public String toString() {
        return "[T]" + super.toString();
    }
}
