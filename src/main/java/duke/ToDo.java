package duke;


public class ToDo extends Task{
    public ToDo(String taskDescription) {
        super(taskDescription.replace("todo ", ""));
    }

    public ToDo(String taskDescription, boolean isCompleted) {
        super(taskDescription, isCompleted);
    }

    @Override
    protected String returnDescription() {
        return "[T]" + super.returnDescription();
    }

    @Override
    protected String toWriteFile() {
        return "T , " + super.toWriteFile();
    }
}
