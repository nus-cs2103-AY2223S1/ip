package task;
import printer.Printer;

public class Task {
    private String description;
    private boolean isFinished;

    public Task(String description) {
        this.description = description;
        this.isFinished = false;
    }

    public void markAsFinished() {
        this.isFinished = true;
        Printer.print(String.format("This task has been marked as done:\n %s",
                this.toString()));
    }

    public void markAsNotFinished() {
        this.isFinished = false;
        Printer.print(String.format("This task has been marked as not done yet:\n %s",
                this.toString()));
    }

    public String getStatusIcon() {
        return this.isFinished ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
