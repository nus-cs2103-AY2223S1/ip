class Task {
    protected String content;
    protected boolean isDone;

    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    public void changeDoneness(boolean doneness) {
        this.isDone = doneness;
    }

    @Override
    public String toString() {
        String doneness = this.isDone? "X" : " ";
        return String.format("[%s] %s", doneness, this.content);
    }
}
