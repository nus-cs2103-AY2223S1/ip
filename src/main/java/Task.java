import java.time.format.DateTimeFormatter;

abstract class Task {
    public static enum Type {
        TODO,
        DEADLINE,
        EVENT
    };

    protected static final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter dateFormatPrint = DateTimeFormatter
            .ofPattern("MMM dd yyyy");
    protected static final DateTimeFormatter dateTimeFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter dateTimeFormatPrint = DateTimeFormatter
            .ofPattern("MMM dd yyyy HH:mm");

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
