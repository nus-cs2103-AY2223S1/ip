package ted.task;

import java.util.Arrays;

import ted.exception.InvalidEncodingException;
import ted.exception.TedException;

/**
 * A class that encapsulate a task
 * Task's encoding format
 * Type | isDone | Message | ...other arguments
 */
public class Task {
    /**
     * Description of task
     */
    private String description;

    /**
     * To indicate whether the task is done
     */
    private Boolean isDone;

    /**
     * Construct a Task isntance
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Mark task that has been done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark task
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Encode to a string that can be stored in file
     * @return string that is store-able
     */
    public String encode() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Decode encoded string of a task to a task instance
     * @param encoded
     * @return a task instance
     * @throws InvalidEncodingException
     */
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

    public boolean contains(String search) {
        return this.description.toLowerCase().contains(search.toLowerCase());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
