public class Task {

    private boolean isDone;
    private String taskDescription;
    private int index;
    private String straightLine = "  ----------------------------------------------------------------------------------";

    public Task(boolean isDone, String taskDescription, int index) {
        this.isDone = isDone;
        this.taskDescription = taskDescription;
        this.index = index;
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return taskDescription;
    }

    public int getIndex() {
        return index;
    }

    public void markDone() {

        this.isDone = true;

        System.out.println(straightLine + "\n  Good Job! You're Killing It!\n  [X] " + taskDescription
                + "\n" + straightLine + "\n");
    }

    public void markUndone() {

        this.isDone = false;

        System.out.println(straightLine + "\n  AAaaa please get it done soon...\n  [ ] " + taskDescription
                + "\n" + straightLine + "\n");
    }

    public void printAdded() {
        System.out.println(straightLine + "\n added: " + taskDescription + "\n" + straightLine + "\n\n");
    }

    public void printTask() {
        if (!isDone) {
            System.out.println("  " + index + ".[ ] " + taskDescription);
        } else {
            System.out.println("  " + index + ".[X] " + taskDescription);
        }

    }

}
