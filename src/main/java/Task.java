import java.time.LocalDate;

public class Task {
    protected String itself;
    protected boolean isDone;

    public Task(String itself) {
        this.itself = itself;
        this.isDone = false;
    }

    public String getStatus() {
        return isDone ? "Y" : "N";
    }

    public void donelah() {
        isDone = true;
    }

    public void nodone() {
        isDone = false;
    }

    public String writeToFile() {
        String status = getStatus();
        return status + "|" + itself.trim();
    }

    public static Task fromFileDescription(String input) {
        return null;
    }
    public boolean isOnDate(LocalDate lc) {
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + itself;
    }
}
