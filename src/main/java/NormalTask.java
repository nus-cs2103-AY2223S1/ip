public class NormalTask extends Task {
    public NormalTask(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toString() {
        return "[ ]" + super.toString();
    }
}
