abstract class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void changeDoneness(boolean doneness) {
        this.isDone = doneness;
    }

    @Override
    public String toString() {
        String doneness = this.isDone? "X" : " ";
        return String.format("[%s] %s", doneness, this.name);
    }
}
