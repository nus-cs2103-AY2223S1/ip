package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidTimeException;
import duke.helper.DateTimeConverter;

/**
 * Main class to create new Task
 */
public class TaskCreator {
    enum Type {
        TODO,
        DEADLINE,
        EVENT
    }
    private static final int SIZE_OF_PREPOSITION = 4;

    /**
     * Method to create an appropriate task given an input string
     * from the command line
     *
     * @param in the input command string to create the task
     * @return the task after it has been created
     */
    public static Task createTask(String in) {
        Task task = null;
        Type type;

        if (!in.startsWith("todo")
                && !in.startsWith("deadline")
                        && !in.startsWith("event")) {
            return null;
        }

        String[] inArr = in.split(" ", 2);

        if (inArr.length < 2) {
            return new Task("", "[]");
        }

        String info = inArr[1];

        if (in.startsWith("todo")) {
            type = Type.TODO;
        } else if (in.startsWith("deadline")) {
            type = Type.DEADLINE;
        } else {
            type = Type.EVENT;
        }

        switch (type) {
        case TODO:
            task = createTodo(info);
            break;

        case DEADLINE:
            task = createDeadline(info);
            break;

        case EVENT:
            task = createEvent(info);
            break;
        default:
            return new Task("", "[]");
        }
        return task;
    }

    /**
     * Method to create an appropriate task given an input line
     * from the .txt file
     *
     * @param line the line given in the .txt file
     * @return the task after it has been created
     */
    public static Task createFromStorage(String line) {
        String[] lineParts = line.split("--");
        String info = "";

        if (lineParts[0].equals("[T]")) {
            info += "todo";
            info += (" " + lineParts[2]);
        } else if (lineParts[0].equals("[D]")) {
            info += "deadline";
            info += (" " + lineParts[2]
                    + " /by " + lineParts[3]);
        } else {
            info += "event";
            info += (" " + lineParts[2]
                    + " /at " + lineParts[3]);
        }

        Task task = createTask(info);

        if (!lineParts[1].equals("[ ]")) {
            task.isDone = true;
        }

        return task;
    }

    private static Task createTodo(String info) {
        return new Todo(info);
    }

    private static Task createDeadline(String info) {
        int indexOfSplit = info.indexOf("/by");
        if (indexOfSplit == -1) {
            return new ErrorTask();
        }
        try {
            String description = info.substring(0, indexOfSplit - 1);
            String[] dateTimeDeadline = info.substring(indexOfSplit
                            + SIZE_OF_PREPOSITION)
                    .split(" ");

            if (dateTimeDeadline.length > 1 && Integer.valueOf(dateTimeDeadline[1]) < 0) {
                return new Deadline("invalidTime", "", null);
            }

            DateTimeFormatter formatter = DateTimeFormatter
                    .ofPattern("uuuu-M-d");

            DateTimeConverter converter = new DateTimeConverter(formatter);
            String date;

            if (!converter.isValidDate(dateTimeDeadline[0])) {
                date = info.substring(indexOfSplit
                        + SIZE_OF_PREPOSITION);

                return new Deadline(description, date, null);
            } else {
                date = converter.convert(dateTimeDeadline);
                return new Deadline(description, date,
                        LocalDate.parse(dateTimeDeadline[0], formatter));
            }
        } catch (StringIndexOutOfBoundsException e) {
            return new Task("", "[]");
        }
    }

    private static Task createEvent(String info) {
        try {
            int indexOfSplit = info.indexOf("/at");
            if (indexOfSplit == -1) {
                return new ErrorTask();
            }

            String description = info.substring(0, indexOfSplit - 1);
            String dateTimeEvent = info.substring(indexOfSplit
                    + SIZE_OF_PREPOSITION);

            return new Event(description, dateTimeEvent);
        } catch (StringIndexOutOfBoundsException e) {
            return new Task("", "[]");
        }
    }
}
