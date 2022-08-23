import java.util.Arrays;

/**
 * A class that encapsulate a task
 * <p>
 * Task's encoding format
 * Type | isDone | Message | ...other arguments
 */
public class Task {
    private String description;
    private Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public String encode() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    public static Task decode(String encoded) throws InvalidEncodingException {
        String[] line = encoded.split(" \\| ");
        if (line.length <= 2) {
            throw new InvalidEncodingException();
        }

        String type = line[0];
        Boolean isDone = line[1].equals("1");
        String[] args = Arrays.copyOfRange(line, 2, line.length);

        Task task = null;
        if (type.equals("T") && args.length == 1) {
            task = (new ToDo(args[0]));
        } else if (type.equals("D") && args.length == 2) {
            try {
                task = (new Deadline(args[0], args[1]));
            } catch (TedException e) {
                throw new InvalidEncodingException();
            }
        } else if (type.equals("E") && args.length == 2) {
            task = (new Event(args[0], args[1]));
        } else {
            throw new InvalidEncodingException();
        }

        if (task != null) {
            if (isDone) {
                task.markAsDone();
            }
        }

        return task;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}