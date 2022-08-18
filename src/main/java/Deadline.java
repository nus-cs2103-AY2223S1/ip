public class Deadline extends Task{
    private String date = "";

    public Deadline(String taskDescription, String date) {
        super(taskDescription.replace("deadline ", ""));
        this.date = date;
    }
    @Override
    protected String returnDescription() {
        return "[D]" + super.returnDescription() + "(by: " + this.date + ")";
    }
}

