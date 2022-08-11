public class Task {
    protected String description;
    protected boolean isDone;
    private static String MARKED_TXT = "Nice ! I've marked this task as done:";
    private static String UNMARKED_TEXT = "OK, I've marked this text as not done yet:";

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    String getMarkedStatus() {
        return isDone ? "[X]" : "[ ]";
    }

    void markAsDone() {
        this.isDone = true;
        String toPrint = String.format("%s\n [X] %s", MARKED_TXT, this.description);
        System.out.println(toPrint);
    }

    void markAsUnDone() {
        this.isDone = false;
        String toPrint = String.format("%s\n [] %s", UNMARKED_TEXT, this.description);
        System.out.println(toPrint);
    }
}
