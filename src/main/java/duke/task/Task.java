package duke.task;

import duke.common.DukeException;

import java.time.LocalDate;

public abstract class Task {
    protected static final String ENCODING_SEPARATOR = " | ";
    protected static final String DECODING_SEPARATOR = " \\| ";


    private String taskType;
    private String description;
    private boolean isDone;

    public Task(String taskType, String description, boolean isDone) throws DukeException {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
        if (this.description.isBlank()) {
            throw new DukeException("OOPS!!! The description of a " + this.taskType + " cannot be empty.");
        }
    }

    public char getStatusIcon() {
        return (isDone ? 'X' : ' '); // mark done task with X
    }

    public char getTaskTypeIcon() {
        return taskType.toUpperCase().charAt(0);
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + this.getTaskTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    public String encode() {
        return this.getTaskTypeIcon() + Task.ENCODING_SEPARATOR + this.getStatusIcon() + Task.ENCODING_SEPARATOR + this.description;
    }

    public static Task decode(String input) throws DukeException {
        String[] args = input.trim().split(" \\| ");
        Task task;
        try {
            switch (args[0]) {
                case "T":
                    task = new ToDo(args[2], args[1].equals("X"));
                    break;

                case "E":
                    task = new Event(args[2], args[1].equals("X"), LocalDate.parse(args[3]));
                    break;

                case "D":
                    task = new Deadline(args[2], args[1].equals("X"), LocalDate.parse(args[3]));
                    break;

                default:
                    throw new DukeException("OOPS!!! Invalid encoded format :(");
            }
        } catch (IndexOutOfBoundsException exception) {
            throw new DukeException("OOPS!!! Invalid encoded format :(");
        } catch (java.time.format.DateTimeParseException exception) {
            throw new DukeException("OOPS!!! Can't recognize the date :(. Please input the date in yyyy-mm-dd format.");
        }
        return task;
    }
}
