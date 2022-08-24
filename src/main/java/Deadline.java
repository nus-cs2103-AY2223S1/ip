public class Deadline extends Task{
    private String time;
    public Deadline(String content, String time) {
        super(content);
        this.time = time;
    }

    public Deadline(String content, String time, boolean isDone) {
        super(content, isDone);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String toString() {
        return this.isDone() ? "[D][X] " + this.getContent() + " by " + this.time
                : "[D][ ] " + this.getContent() + " by " + this.time;
    }
}
