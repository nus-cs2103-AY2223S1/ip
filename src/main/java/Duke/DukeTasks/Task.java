package Duke.DukeTasks;

import java.time.LocalDate;

public class Task {
    private String name;
    private boolean finished = false;

    public Task(String taskname) {
        this.name = taskname;
    }

    public void markAsDone() {
        finished = true;
    }

    @Override
    public String toString() {
        if (finished) {
            return "[/] " + this.name;
        } else {
            return "[X] " + this.name;
        }
    }

    public String fileForm() {
        return this.finished + "," + this.name;
    }

    public boolean compareDate(LocalDate localDate) {
        return false;
    }
}
