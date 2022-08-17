public class ToDos extends Task {
    protected String taskType = "[T]";

    public ToDos(String description) {
        super(description);
    }

    @Override
    public void fullDesc() {
        System.out.println("      " + this.taskType +
                this.getStatusIcon() + " " + this.description);
    }

    @Override
    public String stringDesc() {
        return this.taskType +
                this.getStatusIcon() + " " + this.description;
    }
}
