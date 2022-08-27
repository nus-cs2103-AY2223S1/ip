import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public boolean getStatus() {
        return isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String taskType() {
        return "";
    }

    public static Task stringToTask(String str) {
        String[] line = str.split("\\|");
        if (line[0].equals("Todo      ")) {
            return new ToDo(line[2].trim(), " Done   ".equals(line[1]));
        } else if (line[0].equals("Deadline  ")) {
            return new Deadline(line[2].trim(), " Done   ".equals(line[1]), LocalDate.parse(line[3].trim()));
        } else {
            String[] time = line[3].split("to");
            String start = time[0].trim();
            String end = time[1].trim();
            LocalDate endDate = LocalDate.parse(end);
            LocalDate startDate = LocalDate.parse(start);
            return new Event(line[2].trim(), " Done   ".equals(line[1]), startDate, endDate);
        }
    }
}
