package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    private Boolean isDone;
    private final String description;

    public Task(String description) {
        this.description = description;
        this.markAsNotDone();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getStatus() {
        return this.isDone;
    }

    public String printTask() {
        return String.format("[%s] %s",
                this.getStatus() ? "X" : " ",
                this.getDescription());
    }

    public static char getTaskTypeChar(String entry) {
        return entry.charAt(1);
    }

    public static boolean isMarked(String entry) {
        return entry.charAt(4) == 'X';
    }

    public static <T extends Task> Task parseEntry(String entry, char type) {
        T task = null;
        String dataStr = entry.substring(7);

        switch (type) {
            case 'T':
                task = (T) new Todo(String.format("%s%s", Parser.TASK_KEYWORD_TODO, dataStr));
                break;
            case 'D':
                String deadlineStr = dataStr.substring(0, dataStr.length() - 1);
                deadlineStr = deadlineStr.replace("(by:", "/by");
                String frontStr = deadlineStr.split(Deadline.DELIMITER)[0];
                String dateStr = deadlineStr.split(Deadline.DELIMITER)[1];
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
                LocalDate date = LocalDate.parse(dateStr, formatter);
                dateStr = date.toString();
                String fullStr = frontStr + Deadline.DELIMITER + dateStr;
                task = (T) new Deadline(String.format("%s%s", Parser.TASK_KEYWORD_DEADLINE, fullStr));
                break;
            case 'E':
                String eventStr = dataStr.substring(0, dataStr.length() - 1);
                eventStr = eventStr.replace("(at:", "/at");
                String frontStrE = eventStr.split(Event.DELIMITER)[0];
                String dateStrE = eventStr.split(Event.DELIMITER)[1];
                DateTimeFormatter formatterE = DateTimeFormatter.ofPattern("MMM dd yyyy");
                LocalDate dateE = LocalDate.parse(dateStrE, formatterE);
                dateStrE = dateE.toString();
                String fullStrE = frontStrE + Event.DELIMITER + dateStrE;
                task = (T) new Event(String.format("%s%s", Parser.TASK_KEYWORD_EVENT, fullStrE));
                break;
        }

        if (Task.isMarked(entry)) {
            task.markAsDone();
        }

        return task;
    }
}
