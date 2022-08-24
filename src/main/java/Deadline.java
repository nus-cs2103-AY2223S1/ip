public class Deadline extends Task{
    private String by;
    private String taskType;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "D";
    }


    public String getDescription() {
        return super.getDescription() + " | " + by;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
