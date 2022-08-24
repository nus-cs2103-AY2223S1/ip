import java.time.LocalDate;

public class Task {
    protected String itself;
    protected boolean isDone;

    public Task(String itself) {
        this.itself = itself;
        this.isDone = false;
    }

    public String getStatus() {
        return isDone ? "X" : " ";
    }

    public void donelah() {
        isDone = true;
    }

    public void nodone() {
        isDone = false;
    }

    public boolean isOnDate() {
        return false;
    }

    @Override
    public String toString() {
        return "[" + getStatus() + "] " + itself;
    }
}
