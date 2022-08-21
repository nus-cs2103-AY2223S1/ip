package duke.task;

import duke.DukeException;

import java.time.LocalDate;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public int isDoneToInt() {
        return isDone ? 1 : 0;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public static Task loadToTaskList(String data) throws DukeException {
        String[] dataSplit = data.split(" \\| ", 4);
        char typeOfTask = dataSplit[0].trim().charAt(0);
        boolean isDone = dataSplit[1].equals("1");
        String description = dataSplit[2];
        switch (typeOfTask) {
        case 'T':
            return new ToDo(description, isDone);
        case 'D':
            return new Deadline(description, isDone, LocalDate.parse(dataSplit[3]));
        case 'E':
            return new Event(description, isDone, LocalDate.parse(dataSplit[3]));
        default:
            throw new DukeException("OOPS!!! Unrecognised task type!");
        }
    }

    public String saveStringFormat() {
        return String.format("%d | %s", isDoneToInt(), description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }
}
