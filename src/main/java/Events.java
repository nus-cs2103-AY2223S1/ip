public class Events extends Task {
    protected String taskType = "[E]";
    protected String duration;

    public Events(String description, String d) {
        super(description);
        this.duration = d;
    }

    @Override
    public void fullDesc() {
        System.out.println("      " + this.taskType + this.getStatusIcon() +
                " " + this.description + " (" + this.duration + ")");
    }

    @Override
    public String stringDesc() {
        return this.taskType + this.getStatusIcon() +
                " " + this.description + " (" + this.duration + ")";
    }
}
