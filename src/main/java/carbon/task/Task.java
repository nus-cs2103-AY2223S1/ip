package carbon.task;

import carbon.error.CarbonException;
import carbon.error.CorruptedSavefileException;

import java.util.Arrays;
import java.util.List;

import java.time.format.DateTimeFormatter;

/**
 * Enapsulates information regarding tasks.
 * This is an abstract class, and is extended by Todo, Event, and Deadline.
 */
public abstract class Task {
    public static enum Type {
        TODO,
        DEADLINE,
        EVENT
    };

    protected static enum FormatType {
        READ,
        PRINT
    };
    
    protected static final DateTimeFormatter dateFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter dateFormatPrint = DateTimeFormatter
            .ofPattern("MMM dd yyyy");
    protected static final DateTimeFormatter dateTimeFormat = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter dateTimeFormatPrint = DateTimeFormatter
            .ofPattern("MMM dd yyyy HH:mm");

    private static List<Type> typeKeys = Arrays.asList(Type.values());

    protected static int getTypeKey(Type type) {
        int key = typeKeys.indexOf(type);
        return key;
    }

    /**
     * Decodes text data into a single task.
     *
     * @param data Text data of the encoded task.
     * @return Decoded Task.
     * @throws CarbonException  If the data is invalid.
     */
    public static Task decodeTask(String data) throws CarbonException{
        String[] values = data.split("\\|");

        // should have min 3 segments: type, name, and doneness
        if (values.length < 3 || values.length > 4) {
            throw new CorruptedSavefileException(data);
        }

        // else, continue decoding
        int typeKey = Integer.parseInt(values[0]);
        Type type = typeKeys.get(typeKey);
        boolean isDone = Integer.parseInt(values[1]) == 1;
        String name = values[2];
        String param = values.length == 4 ? values[3] : "";
        Task decodedTask;
        switch (type) {
        case TODO:
            decodedTask = Todo.loadTask(name, isDone);
            break;
        case DEADLINE:
            decodedTask = Deadline.loadTask(name, isDone, param);
            break;
        case EVENT:
            decodedTask = Event.loadTask(name, isDone, param);
            break;
        default:
            // should never reach here
            decodedTask = Todo.createTask("todo _");
        }
        decodedTask.changeDoneness(isDone);
        return decodedTask;
    }

    protected String name;
    protected boolean isDone;

    /**
     * Constructs an instance of a Task.
     * Constructor for an abstract class, not meant to be called directly.
     *
     * @param name Name of the task.
     * @param isDone Whether the task is done or not.
     * @return Task object.
     */
    protected Task(String name, Boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Updates whether the task is done or not.
     *
     * @param isDone Whether the task is done or not.
     */
    public void changeDoneness(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Encodes the task object into a simplified text to be stored.
     * Does not use an actual encoding format.
     *
     * @return Encoded text data.
     */
    public abstract String encode();

    /** @inheritDoc */
    @Override
    public String toString() {
        String doneness = this.isDone? "X" : " ";
        return String.format("[%s] %s", doneness, this.name);
    }
}
