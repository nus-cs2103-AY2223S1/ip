public class Deadlines extends Task {
    protected String taskType = "[D]";
    protected String deadline;

    public Deadlines(String description, String d) {
        super(description);
        this.deadline = d;
    }

    @Override
    public void fullDesc() {
        System.out.println("      " + this.taskType + this.getStatusIcon() +
                " " + this.description + " (" + this.deadline + ")");
    }

    @Override
    public String stringDesc() {
        return this.taskType + this.getStatusIcon() +
                " " + this.description + " (" + this.deadline + ")";
    }
}
