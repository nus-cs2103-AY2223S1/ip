public class ToDo extends Task{
    public ToDo(String taskDescription) {
        super(taskDescription.replace("todo ", ""));
    }

    @Override
    protected String returnDescription() {
        return "[T]" + super.returnDescription();
    }
}
